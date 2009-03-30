package com.om.dvr.fixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.text.ParseException;
import java.util.List;

import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.om.dvr.domain.Program;
import com.om.dvr.domain.Schedule;
import com.om.dvr.factory.BeanFactory;

public class TestAFixture {
    private Schedule schedule;
    private Program program1;
    private Program program2;
    private ProgramScheduler scheduler;

    @Before
    public void retrieveSchedule() {
        scheduler = new ProgramScheduler();
        schedule = BeanFactory.retrieve("schedule", Schedule.class);
        program1 = schedule.createProgram("programName", 4, "Friday", new LocalTime(8, 15), 60);
        program2 = schedule.createProgram("programName", 4, "Monday", new LocalTime(7, 15), 60);
    }

    @After
    public void clearSchedule() {
        schedule.clear();
    }

    @Test
    public void smokeTest1() throws ParseException {

        scheduler.AddEpisodeToNamedOn(program1.id(), "name", "2000/4/4");
        scheduler.AddEpisodeToNamedOn(program1.id(), "name", "2000/4/11");
        scheduler.AddEpisodeToNamedOn(program1.id(), "name", "2000/4/18");

        Episodes e = new Episodes(program1.id());
        List<Object> objs = e.query();
        assertEquals(3, objs.size());
    }

    @Test
    public void verifyEpisodeScheduledToConflictActuallyConflicts() throws ParseException {
        scheduler.AddEpisodeToNamedOn(program1.id(), "name", "2000/4/4");
        assertFalse(scheduler.AddEpisodeToNamedOnAt(program2.id(), "name", "2000/4/4", "8:15"));
    }
}
