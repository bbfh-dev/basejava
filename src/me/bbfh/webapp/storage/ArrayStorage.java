package me.bbfh.webapp.storage;

import me.bbfh.webapp.model.Resume;

import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        if (this.size == this.CAPACITY) {
            System.err.printf("Trying to save() a resume `%s`, but the storage is at its full capacity %d\n", resume, this.CAPACITY);
            return;
        }
        if (this.get(resume.getUUID()) != null) {
            System.err.printf("Trying to save() a resume `%s` which is already present. Use update() instead!\n", resume);
            return;
        }
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
