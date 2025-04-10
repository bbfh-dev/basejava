package me.bbfh.webapp.storage;

import me.bbfh.webapp.exception.ResumeAlreadyExistsException;
import me.bbfh.webapp.exception.ResumeNotFoundException;
import me.bbfh.webapp.exception.StorageOutOfSpaceException;
import me.bbfh.webapp.model.Resume;

public interface Storage {
    void clear();

    void update(Resume resume) throws ResumeNotFoundException;

    void save(Resume resume) throws StorageOutOfSpaceException, ResumeAlreadyExistsException;

    Resume get(String uuid);

    void delete(String uuid) throws ResumeNotFoundException;

    Resume[] getAll();

    int size();
}
