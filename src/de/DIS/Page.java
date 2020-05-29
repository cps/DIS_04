package de.DIS;

import java.io.FileWriter;
import java.io.IOException;

public class Page {

    public static void write (int pageID, String userData) {

        try {
            FileWriter f = new FileWriter("data/" + pageID + ".txt", false);
            f.write(userData);
            f.close();
        } catch  (IOException e) {
            e.printStackTrace();
        }
    }
}
