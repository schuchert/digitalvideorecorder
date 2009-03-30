package com.om.dvr.fixture;

import com.om.dvr.domain.Program;
import com.om.dvr.domain.Schedule;
import com.om.dvr.domain.SeasonPassManager;
import com.om.dvr.factory.BeanFactory;

public class CreateSeasonPass {
    public CreateSeasonPass(String programId) {
        try {
            SeasonPassManager seasonPassManager = BeanFactory.retrieve("seasonPassManager", SeasonPassManager.class);
            Program program = BeanFactory.retrieve("schedule", Schedule.class).getProgramById(programId);
            seasonPassManager.addSeasonPassFor(program);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
