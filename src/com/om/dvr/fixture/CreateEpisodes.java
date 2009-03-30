package com.om.dvr.fixture;

import java.text.ParseException;

public class CreateEpisodes {
    private final ProgramScheduler programScheduler;
    private String programId;
    private String name;
    private String date;

    public CreateEpisodes() {
        programScheduler = new ProgramScheduler();
    }
    
    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void execute() throws ParseException {
        programScheduler.AddEpisodeToNamedOn(programId, name, date);
    }
}
