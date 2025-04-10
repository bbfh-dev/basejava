package me.bbfh.webapp.exception;

import me.bbfh.webapp.model.Resume;

public class ResumeAlreadyExistsException extends AbstractException {

    private final Resume resume;

    public ResumeAlreadyExistsException(Resume resume) {
        this.resume = resume;
    }

    public Resume getResume() {
        return this.resume;
    }

    @Override
    public String getHumanReadableError() {
        return String.format("Resume `%s` is already present in the storage", this.resume);
    }
}
