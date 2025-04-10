package me.bbfh.webapp.exception;

public class ResumeNotFoundException extends AbstractException {
    private final String uuid;

    public ResumeNotFoundException(String uuid) {
        this.uuid = uuid;
    }

    public String getUUID() {
        return this.uuid;
    }

    @Override
    public String getHumanReadableError() {
        return String.format("Resume `uuid=%s` is not present in the storage", this.uuid);
    }
}
