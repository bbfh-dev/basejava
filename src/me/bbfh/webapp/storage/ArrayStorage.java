package me.bbfh.webapp.storage;

import me.bbfh.webapp.model.Resume;

import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume resume, int index) {
        this.storage[this.size++] = resume;
    }

    @Override
    protected int find(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (Objects.equals(this.storage[i].getUUID(), uuid)) {
                return i;
            }
        }

        return -1;
    }
}
