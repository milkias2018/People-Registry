package com.prs.people_registry.exception;

public class PersonNotFoundException extends Throwable {
    public PersonNotFoundException(String personNotFound) {
        super(personNotFound);
    }
}
