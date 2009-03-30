package com.om.dvr.domain;

public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException(String id) {
        super(String.format("Program with ID: %s not found.", id));
    }

    private static final long serialVersionUID = 1L;

}
