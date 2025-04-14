package me.bbfh.webapp.storage;

import me.bbfh.webapp.exception.StorageOutOfSpaceException;
import me.bbfh.webapp.model.Resume;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    @Test(expected = StorageOutOfSpaceException.class)
    public void saveOutOfSpace() throws Exception {
        for (int i = 0; i < 10_001; i++) {
            storage.save(new Resume());
        }
    }
}