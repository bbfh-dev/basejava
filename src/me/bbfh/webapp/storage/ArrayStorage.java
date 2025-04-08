package me.bbfh.webapp.storage;

import me.bbfh.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    private final int capacity = 10_000;
    private final Resume[] storage = new Resume[capacity];

    public void clear() {
        Arrays.fill(this.storage, 0, this.size(), null);
        this.size = 0;
    }

    public void save(Resume resume) {
        this.storage[this.size++] = resume;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (Objects.equals(this.storage[i].getUUID(), uuid)) {
                return this.storage[i];
            }
        }

        return null;
    }

    public void delete(String uuid) {
        if (this.size == 0) {
            return;
        }

        for (int i = 0; i < this.size(); i++) {
            if (Objects.equals(this.storage[i].getUUID(), uuid)) {
                for (; i < this.size() - 1; i++) {
                    this.storage[i] = this.storage[i + 1];
                }
                this.storage[this.size() - 1] = null;
                break;
            }
        }

        this.size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] all = new Resume[this.size()];
        if (this.size() >= 0) {
            System.arraycopy(this.storage, 0, all, 0, this.size());
        }
        return all;
    }

    public int size() {
        return this.size;
    }
}
