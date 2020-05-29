package de.DIS;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PageTest {

    @Test
    void write() {
        Page.write(1,  "This is a test.");
    }
}