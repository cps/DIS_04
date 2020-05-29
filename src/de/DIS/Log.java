package de.DIS;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Log {
    private ObjectMapper mapper = new ObjectMapper();
    private int maxLsn = 0;

    public Log() throws IOException {
        Files.writeString(new File("data/log.json").toPath(), "", StandardOpenOption.CREATE);
        Files.writeString(new File("data/log.json").toPath(), "START_LOG" + "\n", StandardOpenOption.APPEND);
        System.out.println("Log created.");
    }


    synchronized public int addLogEntry(int tid, int pid, String data) {

        int lsn = maxLsn;

        LogEntry log = new LogEntry(lsn, tid, pid, data);

        try {
            String json = mapper.writeValueAsString(log);
            Files.writeString(new File("data/log.json").toPath(), json + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Fehler beim schreiben des Logs:" + e.getMessage());
            e.printStackTrace();
        }

        maxLsn++;

        return lsn;

    }

    public int getMaxLsn(){
        return maxLsn;
    }

    public void setMaxLsn(int lsn){
        this.maxLsn = lsn;
    }

}
