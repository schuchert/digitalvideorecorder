package com.om.dvr.fixture;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalTime;

public class CreateSchedule {
    ProgramScheduler scheduler = new ProgramScheduler();

    private LocalTime startTime;

    public List<?> doTable(List<List<String>> table) throws ParseException {
        scheduler.clearSchedule();
        
        List<String> startTimeRow = table.remove(0);
        setStartTime(startTimeRow);

        for (List<String> channelProgramming : table) {
            processChannelInformation(channelProgramming);
        }

        return Collections.emptyList();
    }

    private void processChannelInformation(List<String> channelProgramming) throws ParseException {
        int channel = Integer.parseInt(channelProgramming.get(0));
        ProgramDescriptionParser parser = new ProgramDescriptionParser(startTime, channel);
        List<ProgramDescription> programDescriptions = parser.parse(channelProgramming.get(1));
        processPrograms(programDescriptions);
    }

    private void processPrograms(List<ProgramDescription> programDescriptions) throws ParseException {
        for (ProgramDescription pd : programDescriptions) {
            String programId = scheduler.CreateProgramNamedOnChannelEveryAtDuration(pd.name, pd.channel, "monday", pd
                    .timeOfDay(), pd.durationInMinutes());
            scheduler.AddEpisodeToNamedOnDuration(programId, pd.name, "2009/4/4", pd.durationInMinutes());
        }
    }

    private void setStartTime(List<String> startTimeRow) {
        String startTimeString = startTimeRow.get(1);
        String[] parts = startTimeString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        startTime = new LocalTime(hours, minutes);
    }

}
