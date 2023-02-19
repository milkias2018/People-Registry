package com.prs.people_registry.exception;

public class ChildNotFoundException extends Throwable {
    public ChildNotFoundException(String childNotFound) {
        super(childNotFound);
    }
}
