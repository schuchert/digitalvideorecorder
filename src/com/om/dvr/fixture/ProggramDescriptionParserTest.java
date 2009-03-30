package com.om.dvr.fixture;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

public class ProggramDescriptionParserTest {
    private ProgramDescriptionParser parser;

    @Before
    public void init() {
        parser = new ProgramDescriptionParser(new LocalTime(1, 0), 5);
    }

    void validate(int channel, String name, int hour, int minutes, ProgramDescription pd) {
        assertEquals(channel, pd.channel);
        assertEquals(name, pd.name);
        assertEquals(hour, pd.startTime.getHourOfDay());
        assertEquals(minutes, pd.startTime.getMinuteOfHour());
    }

    @Test
    public void oneProgramOneHourLong() {
        List<ProgramDescription> programs = parser.parse("aaaa");
        assertEquals(1, programs.size());
        ProgramDescription pd = programs.get(0);
        validate(5, "aaaa", 1, 0, pd);
    }

    @Test
    public void oneProgramWithHourOfNoScheduleBefore() {
        List<ProgramDescription> programs = parser.parse("____bbbb");
        assertEquals(1, programs.size());
        ProgramDescription pd = programs.get(0);
        validate(5, "bbbb", 2, 0, pd);
    }

    @Test
    public void twoOneHourPrograms() {
        List<ProgramDescription> programs = parser.parse("aaaabbbb");
        assertEquals(2, programs.size());
        assertEquals("aaaa", programs.get(0).name);
        assertEquals("bbbb", programs.get(1).name);
    }

    @Test
    public void twoOneHourProgramsWithInitialDelayOf45Minutes() {
        List<ProgramDescription> programs = parser.parse("___aaaabbbb");
        assertEquals(2, programs.size());
        validate(5, "aaaa", 1, 45, programs.get(0));
        assertEquals("bbbb", programs.get(1).name);
    }

    @Test
    public void dashesAreNotPrograms() {
        List<ProgramDescription> programs = parser.parse("___aaaa___bbbb");
        assertEquals(2, programs.size());
        validate(5, "aaaa", 1, 45, programs.get(0));
        validate(5, "bbbb", 3, 30, programs.get(1));
    }
}
