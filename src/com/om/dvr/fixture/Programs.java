package com.om.dvr.fixture;

import java.util.List;

import com.om.dvr.domain.Program;
import com.om.dvr.domain.Schedule;
import com.om.dvr.factory.BeanFactory;
import com.om.query.QueryResultBuilder;
import com.om.query.domain.QueryResult;

public class Programs {
    public List<Object> query() {
        Schedule schedule = BeanFactory.retrieve("schedule", Schedule.class);
        QueryResultBuilder builder = new QueryResultBuilder(Program.class);
        builder.register("scheduleSlot", new ScheduleSlotPropertyInjectorHandler());
        QueryResult result = builder.build(schedule);
        return result.render();
    }
}
