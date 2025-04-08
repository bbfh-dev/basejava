package me.bbfh.webapp.model;

/**
 * Initial resume class
 */
public class Resume {
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
}
