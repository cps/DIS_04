package de.DIS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceManagerTest {

    @Test
    void beginTransaction() {
        PersistenceManager myManager = PersistenceManager.getTheManager();
        int taid = myManager.beginTransaction();
        System.out.println("Gestartete Transaktion: " + taid);

        int taid2 = myManager.beginTransaction();
        System.out.println("Gestartete Transaktion: " + taid2);

        assertNotEquals(taid, taid2);
    }
}