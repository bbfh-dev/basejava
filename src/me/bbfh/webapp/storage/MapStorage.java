package me.bbfh.webapp.storage;

import me.bbfh.webapp.exception.ResumeAlreadyExistsException;
import me.bbfh.webapp.exception.ResumeNotFoundException;
import me.bbfh.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        this.storage.clear();
    }

    @Override
    public void update(Resume resume) throws ResumeNotFoundException {
        if (!this.storage.containsKey(resume.getUUID())) {
            throw new ResumeNotFoundException(resume.getUUID());
        }
        this.storage.put(resume.getUUID(), resume);
    }

    @Override
    public void save(Resume resume) throws ResumeAlreadyExistsException {
        if (this.storage.containsKey(resume.getUUID())) {
            throw new ResumeAlreadyExistsException(resume);
        }
        this.storage.put(resume.getUUID(), resume);
    }

    @Override
    public Resume get(String uuid) {
        return this.storage.get(uuid);
    }

    @Override
    public void delete(String uuid) throws ResumeNotFoundException {
        if (!this.storage.containsKey(uuid)) {
            throw new ResumeNotFoundException(uuid);
        }
        this.storage.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] all = new Resume[this.size()];

        int i = 0;
        for (Map.Entry<String, Resume> entry : this.storage.entrySet()) {
            all[i] = entry.getValue();
            i++;
        }

        return all;
    }

    @Override
    public int size() {
        return this.storage.size();
    }
}
