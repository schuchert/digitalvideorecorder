package com.om.dvr.fixture;

import java.util.List;

import com.om.dvr.domain.Episode;
import com.om.dvr.domain.Program;
import com.om.dvr.domain.Schedule;
import com.om.dvr.factory.BeanFactory;
import com.om.query.QueryResultBuilder;
import com.om.query.domain.QueryResult;

public class Episodes {
    private final String programId;

    public Episodes() {
        programId = "";
    }

    public Episodes(String programId) {
        this.programId = programId;
    }

    public List<Object> query() {
        Schedule schedule = BeanFactory.retrieve("schedule", Schedule.class);
        QueryResultBuilder builder = new QueryResultBuilder(Episode.class);
        builder.register("slot", new EpisodeTimeSlotHandler());

        if (programId == "") {
            QueryResult result = builder.build(schedule.episodes());
            return result.render();
        } else {
            Program program = schedule.getProgramById(programId);
            QueryResult result = builder.build(program.getEpisodes());
            return result.render();
        }
    }
}
