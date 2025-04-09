package me.bbfh.webapp.storage;

import me.bbfh.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        if (this.size == this.CAPACITY) {
            System.err.printf("Trying to save() a resume `%s`, but the storage is at its full capacity %d\n", resume, this.CAPACITY);
            return;
        }

        int index = Arrays.binarySearch(this.storage, 0, this.size, resume);
        if (index >= 0) {
            System.err.printf("Trying to save() a resume `%s` which is already present. Use update() instead!\n", resume);
            return;
        }
        index = -(index + 1); // As defined by the binarySearch() behavior

        for (int i = this.size; i > index; i--) {
            this.storage[i] = this.storage[i - 1];
        }
        this.storage[index] = resume;
        this.size++;
    }

    @Override
    protected int find(String uuid) {
        Resume target = new Resume();
        target.setUUID(uuid);
        return Arrays.binarySearch(this.storage, 0, this.size, target);
    }
}
