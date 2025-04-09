package me.bbfh.webapp.model;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    // Unique identifier
    private String uuid;

    @Override
    public String toString() {
        return uuid;
    }

    public String getUUID() {
        return this.uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int compareTo(Resume resume) {
        return this.uuid.compareTo(resume.uuid);
    }
}
