package com.om.dvr.fixture;

public class BadlyFormedChannelCommanName extends RuntimeException {
    public BadlyFormedChannelCommanName(String channelCommaName) {
        super(String.format("Should be <number>,<string>, but is: '%s'", channelCommaName));
    }

    private static final long serialVersionUID = 1L;
}
