package me.bbfh.webapp.storage;

import me.bbfh.webapp.exception.ResumeAlreadyExistsException;
import me.bbfh.webapp.exception.ResumeNotFoundException;
import me.bbfh.webapp.exception.StorageOutOfSpaceException;
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

    public void update(Resume resume) throws ResumeNotFoundException {
        int index = this.find(resume.getUUID());
        if (index == -1) {
            throw new ResumeNotFoundException(resume.getUUID());
        }

        this.storage[index] = resume;
    }

    public void save(Resume resume) throws StorageOutOfSpaceException, ResumeAlreadyExistsException {
        if (this.size == this.CAPACITY) {
            throw new StorageOutOfSpaceException(resume, this.CAPACITY);
        }
        int index = this.find(resume.getUUID());
        if (index >= 0) {
            throw new ResumeAlreadyExistsException(resume);
        }
        this.insertElement(resume, index);
        this.size++;
    }

    public void delete(String uuid) throws ResumeNotFoundException {
        int i = this.find(uuid);
        if (i < 0) {
            throw new ResumeNotFoundException(uuid);
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
        if (index < 0) {
            return null;
        }
        return this.storage[index];
    }

    protected abstract int find(String uuid);

    protected abstract void insertElement(Resume resume, int index);
}
