package de.DIS;

import java.io.FileWriter;
import java.io.IOException;

public class Page {

    public static void write (int pageID, int lsn, String userData) throws IOException {
        FileWriter f = new FileWriter("data/page" + pageID + ".txt", false);
        try {
            f.write(lsn +","+ userData);
            f.close();
        } catch  (IOException e) {
            e.printStackTrace();
        }
    }
}
