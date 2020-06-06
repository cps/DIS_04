package de.DIS;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @todo Constructor: Get Persistence Manager singleton
 * @todo Write actions as given by parameter with timer in between
 */
public class Client implements Runnable{

    private int index = 0;
    public ArrayList<Consumer> toExecute = new ArrayList();

    public Client(int index){
        this.index = index;
    }

    public void run(){
        System.out.println("Running stored commands.");
        runStoredCommandsWithTimer();
        System.out.println("Running hardcoded commands");
        executeTask(this.index);
    }

    private static void executeTask(int index){
        try{
            Random r = new Random();
            PersistenceManager pm = PersistenceManager.getTheManager();
            int tid = -1;
            switch (index) {
                case 0:
                    break;
                case 1: tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 1, "testData1");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 2, "testData2");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 3, "testData3");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 4, "testData4");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 1, "testData1.1");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 5, "testData5");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));


                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 6, "testData6");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));


                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 7, "testData7");
                    break;
                case 2: tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 8, "testData8");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 9, "testData9");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 10, "testData10");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 11, "testData11");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 8, "testData8.1");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 12, "testData12");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));

                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 13, "testData13");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));

                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 14, "testData14");
                    break;
                case 3: tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 15, "testData15");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 16, "testData16");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 17, "testData17");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 18, "testData18");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 15, "testData15.1");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 19, "testData19");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));


                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 20, "testData20");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));


                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 21, "testData21");
                    break;
                case 4: tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 22, "testData22");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 23, "testData23");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 24, "testData24");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 25, "testData25");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 22, "testData22.1");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 26, "testData26");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));


                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 27, "testData27");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));


                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 28, "testData28");
                    break;
                case 5: tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 29, "testData29");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 30, "testData30");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 31, "testData31");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 32, "testData32");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 29, "testData29.1");
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 33, "testData33");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));


                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 34, "testData34");
                        Thread.sleep(r.nextInt(1000));
                        pm.commit(tid);
                        Thread.sleep(r.nextInt(1000));


                        tid = pm.beginTransaction();
                        Thread.sleep(r.nextInt(1000));
                        pm.write(tid, 35, "testData35");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void runStoredCommandsWithTimer(){
        Random r = new Random();
        for (Consumer c: toExecute){
            System.out.println("Sending command to the manager.");
            c.accept(PersistenceManager.getTheManager());
            try {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
