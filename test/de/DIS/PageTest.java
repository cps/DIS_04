package de.DIS;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PageTest {

    @Test
    void write() throws IOException {
        Page.write(01, 667, "This is a test.");
    }
}