package me.bbfh.webapp.storage;

import me.bbfh.webapp.exception.ResumeAlreadyExistsException;
import me.bbfh.webapp.exception.ResumeNotFoundException;
import me.bbfh.webapp.exception.StorageOutOfSpaceException;
import me.bbfh.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume("UUID_1"));
        storage.save(new Resume("UUID_2"));
        storage.save(new Resume("UUID_3"));
    }

    @Test
    public void clear() throws Exception {
        Assert.assertNotEquals(0, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_2);
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_2));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void updateNotFound() throws Exception {
        storage.update(new Resume("UUID_UNKNOWN"));
    }

    @Test
    public void save() throws Exception {
        String uuid = UUID.randomUUID().toString();
        storage.save(new Resume(uuid));
        Assert.assertEquals(uuid, storage.get(uuid).getUUID());
    }

    @Test(expected = StorageOutOfSpaceException.class)
    public void saveOutOfSpace() throws Exception {
        for (int i = 0; i < 10_001; i++) {
            storage.save(new Resume());
        }
    }

    @Test(expected = ResumeAlreadyExistsException.class)
    public void saveAlreadyExists() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_2);
        Assert.assertNull(storage.get(UUID_2));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void deleteNotFound() throws Exception {
        storage.delete("UUID_UNKNOWN");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] all = storage.getAll();
        Assert.assertEquals(3, all.length);
        Assert.assertEquals(UUID_1, all[0].getUUID());
        Assert.assertEquals(UUID_2, all[1].getUUID());
        Assert.assertEquals(UUID_3, all[2].getUUID());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(UUID_1, storage.get(UUID_1).getUUID());
        Assert.assertEquals(UUID_2, storage.get(UUID_2).getUUID());
        Assert.assertEquals(UUID_3, storage.get(UUID_3).getUUID());
    }
}