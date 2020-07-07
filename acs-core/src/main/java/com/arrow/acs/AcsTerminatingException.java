package com.arrow.acs;

public class AcsTerminatingException extends AcsLogicalException {
    private static final long serialVersionUID = -6053868676604047446L;

    public AcsTerminatingException() {
        super("Application is terminating!");
    }
}
