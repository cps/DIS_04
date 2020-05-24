package de.DIS;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    @Test
    void addLogEntry() throws IOException {
        Log log = new Log();
        log.addLogEntry(1, 1,"test");
        log.addLogEntry(1,1, "test2");
    }


}