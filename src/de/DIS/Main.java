package de.DIS;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    /**
     * @TODO Start & Initialize persistence manager
     * @TODO Start clients
     * @TODO Perform writes
     * @TODO Clean up for next start
     */
    public static void main(String[] args) {

        int reset = 1;
        int recovery = 0;
        int start = 0;

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client(1));
        clients.add(new Client(2));
        clients.add(new Client(3));
        clients.add(new Client(4));
        clients.add(new Client(5));

        if(reset > 0){
            clear();
        }
        if(recovery > 0){
            PersistenceManager.getTheManager().recovery();
        }
        if(start > 0){
            start(clients);
        }
    }

    private static void start(ArrayList<Client> clients){

        for(Client client : clients){
            client.start();
        }
    }

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
