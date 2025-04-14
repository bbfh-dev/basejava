package me.bbfh.webapp.storage;

public abstract class AbstractStorage implements Storage {
    protected int size = 0;

    public int size() {
        return this.size;
    }
}
