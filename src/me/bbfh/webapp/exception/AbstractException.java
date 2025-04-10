package me.bbfh.webapp.exception;

public abstract class AbstractException extends Exception {
    abstract public String getHumanReadableError();
}
