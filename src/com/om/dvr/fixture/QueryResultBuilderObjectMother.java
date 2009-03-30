package com.om.dvr.fixture;

import com.om.dvr.domain.Episode;
import com.om.query.QueryResultBuilder;

public class QueryResultBuilderObjectMother {
    public static QueryResultBuilder builderWithyyyyMdTranslated() {
        QueryResultBuilder queryResultBuilder = new QueryResultBuilder(Episode.class);
        queryResultBuilder.register("slot", new EpisodeTimeSlotHandler());
        return queryResultBuilder;
    }
}
