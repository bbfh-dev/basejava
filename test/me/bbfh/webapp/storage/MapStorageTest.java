package me.bbfh.webapp.storage;

import me.bbfh.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {
    @Before
    public void setUp() throws Exception {
        storage = new MapStorage();
        super.setUp();
    }

    @Test
    public void getAll() throws Exception {
        Resume[] all = storage.getAll();
        Assert.assertEquals(3, all.length);
        Assert.assertNotNull(all[0].getUUID());
        Assert.assertNotNull(all[1].getUUID());
        Assert.assertNotNull(all[2].getUUID());
    }
}
