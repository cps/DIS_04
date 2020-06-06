package de.DIS;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    /**
     * Creates and starts multiple clients that perform some database actions.
     * Variables reset, recovery, start allow to set the mode in which the program is run.
     */
    public static void main(String[] args) {

        boolean reset = false;
        boolean recovery = false;
        boolean start = true;

        // Create list of clients to be started, if start is set.
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client(1));
        clients.add(new Client(2));
        clients.add(new Client(3));
        clients.add(new Client(4));
        clients.add(new Client(5));

        if(reset){
            clear();
        }
        if(recovery){
            PersistenceManager.getTheManager().recovery();
        }
        if(start){
            start(clients);
        }
    }

    /**
     * Starts a given list of clients as separate threads
     * @param clients List of clients to start
     */
    private static void start(ArrayList<Client> clients){

        for(Client client : clients){
            client.start();
        }
    }

    /**
     * Clears pages and logfile to allow a fresh start.
     */
    private static void clear(){

        try{
            File dataFolder = new File("data");

            deleteFolder(dataFolder);
            dataFolder.mkdir();

            int pageCounter = 35;

            for(int i = 1; i <= pageCounter; i++){
                new File("data/"+i+".txt").createNewFile();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Helper to delete a given folder recursively.
     * @param folder Folder to delete
     */
    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
}
