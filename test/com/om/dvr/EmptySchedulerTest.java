package com.om.dvr;

import static org.junit.Assert.assertNotNull;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import com.om.dvr.domain.Program;
import com.om.dvr.domain.ProgramNotFoundException;
import com.om.dvr.domain.Schedule;

public class EmptySchedulerTest {
    private Schedule schedule;

    @Before
    public void init() {
        schedule = new Schedule();
    }

    @Test(expected = ProgramNotFoundException.class)
    public void FindByIdThrowsExcpeionWhenNotFound() {
        schedule.getProgramById("name333");
    }

    @Test
    public void FindingProgramByIdDoesNotThrowExcptionWhenFound() {
        Program createdProgram = schedule.createProgram("name", 42, "friday", new LocalTime(21, 0), 15);
        Program program = schedule.getProgramById(createdProgram.id());

        assertNotNull(program);
    }
}
