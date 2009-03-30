package com.om.dvr.fixture;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ThisScheduleGeneratesTheseTest {
    @Before
    public void init() throws Exception {
        ProgramScheduler scheduler = new ProgramScheduler();
        scheduler.clearSchedule();
        String programH = scheduler.CreateProgramNamedOnChannelEveryAtDuration("hhh", 200, "monday", "1:00", 45);
        String programK = scheduler.CreateProgramNamedOnChannelEveryAtDuration("kkkk", 247, "monday", "1:30", 60);
        scheduler.AddEpisodeToNamedOn(programH, "hhh", "2004/4/4");
        scheduler.AddEpisodeToNamedOn(programK, "kkkk", "2004/4/4");
    }

    @Test
    public void canAddAndFindOne() throws Exception {
        ThisScheduleGeneratesThese thisScheduleGeneratesThese = new ThisScheduleGeneratesThese(1);
        thisScheduleGeneratesThese.create("247:k,200:h");
        assertTrue(thisScheduleGeneratesThese.verifyToDoListHasExactly("247:k"));
    }

    @Test
    public void cannotAddAnyIfNoRecorders() {
        ThisScheduleGeneratesThese thisScheduleGeneratesThese = new ThisScheduleGeneratesThese(0);
        thisScheduleGeneratesThese.create("200:h");
        assertTrue(thisScheduleGeneratesThese.verifyToDoListHasExactly(""));
    }
}
