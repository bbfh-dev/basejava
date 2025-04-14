package me.bbfh.webapp.storage;

import org.junit.Before;

public class ListStorageTest extends AbstractStorageTest {
    @Before
    public void setUp() throws Exception {
        storage = new ListStorage();
        super.setUp();
    }
}
