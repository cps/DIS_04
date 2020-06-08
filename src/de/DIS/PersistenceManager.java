package de.DIS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class PersistenceManager {

    /**
     * Persistence Manager singleton. Access via getTheManager()
     */
    static final private PersistenceManager theManager;
    static {
        try {
            theManager = new PersistenceManager();
        } catch (Throwable e){
            throw new RuntimeException(e.getMessage());
        }
    }
    private Hashtable<Integer, Integer> transaction_status = new Hashtable<>();
    private Hashtable<Integer, Integer> page_transaction = new Hashtable<>();
    private Hashtable<Integer, String> page_data = new Hashtable<>();
    private int bufferSize = 5;
    private Log log = new Log();
    private int maxTid = 0;

    private PersistenceManager() throws IOException {}

    /**
     * Access Persistence Manager singleton.
     * @return The currently active persistence manager
     */
    static public PersistenceManager getTheManager(){
        return theManager;
    }

    /**
     * Starts a new transaction.
     * @return The persistence manager creates a unique transaction ID and returns it to the client.
     */
    public synchronized int beginTransaction() {
        int taid = maxTid;
        transaction_status.put(taid, 0);
        log.addLogEntry(taid, -1, "#START#");
        maxTid++;
        return taid;
    }

    /**
     * Commits the transaction specifi􏰂ed by the given transaction ID.
     * @param taid Transaction ID
     */
    public synchronized void commit(int taid){
        transaction_status.put(taid, 1);
        log.addLogEntry(taid, -1, "#EOT#");
    }

    /**
     * Writes the given data with the given page ID on behalf of the given transaction to the bu􏰔er.
     * If the given page already exists, its content is replaced completely by the given data.
     * @param taid Transaction ID
     * @param pageid Page ID to write
     * @param data Data to write
     */
    public synchronized void write(int taid, int pageid, String data){
        int lsn = log.addLogEntry(taid, pageid, data);
        page_transaction.put(pageid, taid);
        page_data.put(pageid, lsn +","+ data);

        bufferCheck();
    }

    /**
     * If buffer size is reached, writes all pages to persistent storage.
     * Written pages are removed from buffer, read-operation is not implemented anyway.
     */
    private synchronized void bufferCheck(){
        if(page_data.size() > bufferSize){
            Set<Integer> toRemove = new HashSet<>();

            Set<Integer> pages = page_data.keySet();
            for(Integer page : pages){
                int transaction = page_transaction.get(page);
                int status = transaction_status.get(transaction);
                if(status > 0){
                    Page.write(page, page_data.get(page));
                    toRemove.add(page);
                }
            }
            pages.removeAll(toRemove);
            toRemove.clear();

            Set<Integer> transactions = transaction_status.keySet();
            for(Integer transaction : transactions){
                int status = transaction_status.get(transaction);
                if(status > 0){
                    toRemove.add(transaction);
                }
            }
            transactions.removeAll(toRemove);
        }
    }

    public void recovery(){
        try{
            //Initialize temporary Counters
            int maxLsn = log.getMaxLsn();
            int maxTid = this.maxTid;

            //Open Log File
            File file = new File("data/log.json");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            //Loop LogFile and identify winner transactions and find last LSN
            List<Integer> winnerTas = new ArrayList<>();
            while((line = br.readLine()) != null){
                if(line.startsWith("{")){
                    ObjectMapper mapper = new ObjectMapper();
                    LogEntry le = mapper.readValue(line, LogEntry.class);
                    if(le.lsn > maxLsn){maxLsn = le.lsn;}
                    if(le.data.equals("#EOT#")){winnerTas.add(le.tid);}
                }
            }
            //Set maxLSN in the Log class
            log.setMaxLsn(maxLsn+1);

            //Loop Log File again
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while((line = br.readLine()) != null){
                if(line.startsWith("{")){
                    ObjectMapper mapper = new ObjectMapper();
                    LogEntry le = mapper.readValue(line, LogEntry.class);
                    //If logEntry is a Write and part of a winner transaction
                    if(le.pid != -1 && winnerTas.contains(le.tid)){
                        if(le.tid > maxTid){maxTid = le.tid;}
                        //Find page written by logEntry
                        File page = new File("data/"+le.pid+".txt");
                        //If page not available, create new Page File
                        if(!page.exists()){
                            page.createNewFile();
                        }
                        //Open pageFile
                        FileReader pfr = new FileReader(page);
                        BufferedReader pbr = new BufferedReader(pfr);
                        String[] data = new String[2];
                        //Read content of page into a String array
                        if((line = pbr.readLine()) != null) {
                            data = line.split(",");
                        }else{
                            data[0] = "-1";
                        }
                        //If LSN of Log equals LSN of pageFile
                        if(le.lsn == Integer.parseInt(data[0])){
                            //If data of Log is different from data of pageFile
                            if(!le.data.equals(data[1])){
                                //Redo Write
                                Page.write(le.pid, le.lsn + ","+ le.data);
                            }
                        }
                        //If LSN of Log is higher than LSN of Page
                        if(le.lsn > Integer.parseInt(data[0])){
                            //Redo Write
                            Page.write(le.pid, le.lsn + ","+ le.data);
                        }
                    }
                }
            }
            //Set maxTid of PersistenceManager
            this.maxTid = maxTid+1;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
