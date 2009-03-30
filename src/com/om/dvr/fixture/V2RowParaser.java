package com.om.dvr.fixture;

import java.util.List;

import org.joda.time.LocalTime;

public class V2RowParaser {
    private final LocalTime startTime;
    private final int channel;

    public V2RowParaser(LocalTime startTime, int channel) {
        this.startTime = startTime;
        this.channel = channel;
    }

    public List<ProgramDescription> parse(List<String> row) {
        StringBuilder builder = new StringBuilder();
        for (String s : row)
            builder.append(s);

        ProgramDescriptionParser parser = new ProgramDescriptionParser(startTime, channel);
        return parser.parse(builder.toString());
    }

}
