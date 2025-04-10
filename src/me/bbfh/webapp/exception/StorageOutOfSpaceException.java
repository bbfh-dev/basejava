package me.bbfh.webapp.exception;

import me.bbfh.webapp.model.Resume;

public class StorageOutOfSpaceException extends AbstractException {
    private final Resume resume;
    private final int capacity;

    public StorageOutOfSpaceException(Resume resume, int capacity) {
        this.resume = resume;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Resume getResume() {
        return this.resume;
    }

    @Override
    public String getHumanReadableError() {
        return String.format(
                "failed to insert resume `%s` because the storage is at its full capacity (%d)",
                this.resume,
                this.capacity
        );
    }
}
