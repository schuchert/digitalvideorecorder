package com.om.dvr.fixture;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.om.dvr.domain.EpisodeConflictsWithExistingEpisode;
import com.om.dvr.domain.Program;
import com.om.dvr.domain.Schedule;
import com.om.dvr.domain.ScheduleSlotOccupiedException;
import com.om.dvr.factory.BeanFactory;

public class ProgramScheduler {
    public ProgramScheduler() {
    }

    private Schedule getSchedule() {
        return BeanFactory.retrieve("schedule", Schedule.class);
    }

    public String CreateProgramNamedOnChannelEveryAtDuration(String programName, int channel, String dayOfWeek,
            String timeOfDay, int durationInMinutes) {
        LocalTime time = new LocalTime(timeOfDay);
        Program program = getSchedule().createProgram(programName, channel, dayOfWeek, time, durationInMinutes);
        return program.id();
    }

    public boolean TryCreatingProgramNamedOnChannelEveryAt(String programName, int channel, String dayOfWeek,
            String timeOfDay) {
        try {
            LocalTime time = new LocalTime(timeOfDay);
            getSchedule().createProgram(programName, channel, dayOfWeek, time, 60);
        } catch (ScheduleSlotOccupiedException e) {
            return false;
        }

        return true;
    }

    public boolean AddEpisodeToNamedOn(String id, String episodeName, String date) throws ParseException {
        return AddEpisodeToNamedOnDuration(id, episodeName, date, 60);
    }

    public boolean AddEpisodeToNamedOnDuration(String id, String episodeName, String date, int durationInMinutes)
            throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date programDate = format.parse(date);
        getSchedule().addEpisodeTo(id, episodeName, new LocalDate(programDate), durationInMinutes);
        return true;
    }

    public boolean AddEpisodeToNamedOnAt(String id, String episodeName, String date, String time) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date programDate = format.parse(date);
        String[] split = time.split(":");
        LocalTime localTime = new LocalTime(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        int duration = getSchedule().getProgramById(id).slot.durationInMintues;
        try {
            getSchedule().addEpisodeTo(id, episodeName, new LocalDate(programDate), localTime, duration);
        } catch (EpisodeConflictsWithExistingEpisode e) {
            return false;
        }
        return true;
    }

    public void clearSchedule() {
        getSchedule().clear();
    }

    public List<Program> findProgramMatching(int channel, String partialName) {
        return getSchedule().findProgramMatching(channel, partialName);
    }
}
