package com.om.dvr.fixture;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.LocalTime;

public class ProgramDescriptionParser {

    private static final int MINS_PER_CHAR = 15;
    private final LocalTime startTime;
    private final int channel;

    public ProgramDescriptionParser(LocalTime startTime, int channel) {
        this.startTime = startTime;
        this.channel = channel;
    }

    public List<ProgramDescription> parse(String allPrograms) {
        List<ProgramDescription> result = new LinkedList<ProgramDescription>();

        int programIndex = determineIndexOfFirstProgram(allPrograms);
        LocalTime programStartTime = startTime.plusMinutes(MINS_PER_CHAR * programIndex);

        int lastStartIndex = programIndex;
        char lastChar = allPrograms.charAt(lastStartIndex);
        
        for (int i = programIndex + 1; i < allPrograms.length(); ++i) {
            if (isNewProgram(allPrograms.charAt(i), lastChar)) {
                String name = allPrograms.substring(lastStartIndex, i);
                if (name.indexOf("_") < 0) {
                    result.add(new ProgramDescription(channel, name, programStartTime));
                }
                programStartTime = programStartTime.plusMinutes(name.length() * MINS_PER_CHAR);
                lastStartIndex = i;
                lastChar = allPrograms.charAt(i);
            }
        }

        String name = allPrograms.substring(lastStartIndex);
        result.add(new ProgramDescription(channel, name, programStartTime));
        return result;
    }
    
    private int determineIndexOfFirstProgram(String allPrograms) {
        int programIndex = 0;
        while (programIndex < allPrograms.length() && allPrograms.charAt(programIndex) == '_')
            ++programIndex;
        return programIndex;
    }
    
    private boolean isNewProgram(char current, char last) {
        return current != last;
    }
}
