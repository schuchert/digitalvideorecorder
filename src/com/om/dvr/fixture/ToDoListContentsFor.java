package com.om.dvr.fixture;

import java.util.Iterator;
import java.util.List;

import com.om.dvr.domain.Episode;
import com.om.dvr.domain.SeasonPassManager;
import com.om.dvr.factory.BeanFactory;
import com.om.query.QueryResultBuilder;

public class ToDoListContentsFor {

    private final String programId;
    private final QueryResultBuilder queryResultBuilder;

    public ToDoListContentsFor(String programId) {
        this.programId = programId;
        queryResultBuilder = QueryResultBuilderObjectMother.builderWithyyyyMdTranslated();
    }

    public Iterator<Episode> getScheduledEpisodes() {
        SeasonPassManager seasonPassManager = BeanFactory.retrieve("seasonPassManager", SeasonPassManager.class);
        return seasonPassManager.episodes(programId);
    }
    
    public List<Object> query() {
        return queryResultBuilder.build(getScheduledEpisodes()).render();
    }
}