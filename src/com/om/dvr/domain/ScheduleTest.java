package com.om.dvr.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalTime;
import org.junit.Test;

public class ScheduleTest {
    @Test
    public void canFindProgramByChannelAndPartialName() {
        Schedule schedule = new Schedule();
        schedule.createProgram("ppp", 222, "friday", new LocalTime(12, 15), 60);
        List<Program> programs = schedule.findProgramMatching(222, "p");
        assertEquals(1, programs.size());
    }
}
