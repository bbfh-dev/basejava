package me.bbfh.webapp.storage;

import me.bbfh.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected int size = 0;
    protected final int CAPACITY = 10_000;
    protected final Resume[] storage = new Resume[CAPACITY];

    public void clear() {
        Arrays.fill(this.storage, 0, this.size, null);
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
        if (this.size == this.CAPACITY) {
            System.err.printf("Trying to save() a resume `%s`, but the storage is at its full capacity %d\n", resume, this.CAPACITY);
            return;
        }
        int index = this.find(resume.getUUID());
        if (index >= 0) {
            System.err.printf("Trying to save() a resume `%s` which is already present. Use update() instead!\n", resume);
            return;
        }
        this.insertElement(resume, index);
        this.size++;
    }

    public void delete(String uuid) {
        int i = this.find(uuid);
        if (i == -1) {
            System.err.printf("Trying to delete() a resume with uuid `%s`, but it's not present in storage\n", uuid);
            return;
        }

        for (; i < this.size - 1; i++) {
            this.storage[i] = this.storage[i + 1];
        }
        this.storage[this.size - 1] = null;
        this.size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(this.storage, 0, this.size);
    }

    public int size() {
        return this.size;
    }

    public Resume get(String uuid) {
        int index = this.find(uuid);
        if (index == -1) {
            return null;
        }
        return this.storage[index];
    }

    protected abstract int find(String uuid);

    protected abstract void insertElement(Resume resume, int index);
}
