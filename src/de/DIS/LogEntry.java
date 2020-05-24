package de.DIS;

public class LogEntry {
    public int lsn;
    public int tid;
    public int pid;
    public String data;

    public LogEntry(int lsn, int tid, int pid, String data) {
        this.lsn = lsn;
        this.tid = tid;
        this.pid = pid;
        this.data = data;
    }
}
