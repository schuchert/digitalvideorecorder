package com.om.dvr.fixture;

public class CreatePrograms {
    private final ProgramScheduler programScheduler;
    private String name;
    private int channel;
    private String dayOfWeek;
    private String timeOfDay;
    private int durationInMinutes;
    private String lastIdCreated;

    public CreatePrograms() {
        programScheduler = new ProgramScheduler();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String id() {
        return lastIdCreated;
    }

    public void execute() {
        lastIdCreated = programScheduler.CreateProgramNamedOnChannelEveryAtDuration(name, channel, dayOfWeek,
                timeOfDay, durationInMinutes);
    }
}
