package de.DIS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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
    private ArrayList<Integer> activeTransactions = new ArrayList<>();
    private Log log = new Log();

    /**
     * Create and initialize new persistence manager.
     * @TODO Initialize Text files for pages & logs
     */
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
     * @return The persistence manager creates a unique tran- saction ID and returns it to the client.
     */
    public synchronized int beginTransaction() {
        int taid = findTaid();
        activeTransactions.add(taid);
        log.addLogEntry(taid, 0, "#START#");
        return taid;
    }

    /**
     * Commits the transaction speci􏰂ed by the given transaction ID.
     * @param taid Transaction ID
     */
    public void commit(int taid){

    }

    /**
     * Writes the given data with the given page ID on behalf of the given transaction to the bu􏰔er. If the given page already exists, its content is replaced completely by the given data.
     * @param taid Transaction ID
     * @param pageid Page ID to write
     * @param data Data to write
     */
    public void write(int taid, int pageid, String data){

    }

    /**
     * Finds first currently unused transaction ID and returns it.
     * Note: This method is only called in a synchronized context, therefore it is not synchronized itself.
     * @return Transaction ID as integer.
     */
    private int findTaid(){
        Integer taid = 1;
        boolean found = false;

        while(!found){
            if(activeTransactions.contains(taid)){
                taid++;
            } else {
                found = true;
            }
        }
        return taid;
    }

}
