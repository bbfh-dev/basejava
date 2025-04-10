package me.bbfh.webapp;

import me.bbfh.webapp.exception.AbstractException;
import me.bbfh.webapp.model.Resume;
import me.bbfh.webapp.storage.ArrayStorage;
import me.bbfh.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) throws AbstractException {
        Resume r1 = new Resume();
        r1.setUUID("uuid1");
        Resume r2 = new Resume();
        r2.setUUID("uuid2");
        Resume r3 = new Resume();
        r3.setUUID("uuid3");
        Resume r4 = new Resume();
        r4.setUUID("uuid2");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUUID()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(r4);
        System.out.printf("Get r2 after update: %b", ARRAY_STORAGE.get(r2.getUUID()) == r4);

        printAll();
        ARRAY_STORAGE.delete(r1.getUUID());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
