package me.bbfh.webapp.storage;

import me.bbfh.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume resume, int index) {
        index = -(index + 1); // As defined by the binarySearch() behavior

        System.arraycopy(this.storage, index, this.storage, index + 1, this.size - index);
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
