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

    public void update(Resume resume) {
        int index = this.find(resume.getUUID());
        if (index == -1) {
            System.err.printf("Trying to update() a resume `%s`, but it's not present in storage\n", resume);
            return;
        }

        this.storage[index] = resume;
    }

    public void save(Resume resume) {
        if (this.size == capacity) {
            System.err.printf("Trying to save() a resume `%s`, but the storage is at its full capacity %d\n", resume, this.capacity);
            return;
        }
        if (this.get(resume.getUUID()) != null) {
            System.err.printf("Trying to save() a resume `%s` which is already present. Use update() instead!\n", resume);
            return;
        }
        this.storage[this.size++] = resume;
    }

    public Resume get(String uuid) {
        int index = this.find(uuid);
        if (index == -1) {
            return null;
        }
        return this.storage[index];
    }

    public void delete(String uuid) {
        int i = this.find(uuid);
        if (i == -1) {
            System.err.printf("Trying to delete() a resume with uuid `%s`, but it's not present in storage\n", uuid);
            return;
        }

        for (; i < this.size() - 1; i++) {
            this.storage[i] = this.storage[i + 1];
        }
        this.storage[this.size() - 1] = null;
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

    private int find(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (Objects.equals(this.storage[i].getUUID(), uuid)) {
                return i;
            }
        }

        return -1;
    }
}
