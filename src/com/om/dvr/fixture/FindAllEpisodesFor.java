package com.om.dvr.fixture;

import java.util.List;

import com.om.dvr.domain.Program;
import com.om.dvr.domain.Schedule;
import com.om.dvr.factory.BeanFactory;
import com.om.query.domain.QueryResult;

public class FindAllEpisodesFor {
    private final String programId;

    public FindAllEpisodesFor(String programId) {
        this.programId = programId;
    }

    public List<Object> query() {
        Schedule schedule = BeanFactory.retrieve("schedule", Schedule.class);
        Program program = schedule.getProgramById(programId);
        QueryResult result = QueryResultBuilderObjectMother.builderWithyyyyMdTranslated().build(program.getEpisodes());
        return result.render();
    }
}