package com.om.dvr.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.om.dvr.util.TimeUtil;

public class Schedule implements Iterable<Program> {
    Map<ScheduleSlot, Program> programsBySlot = new ConcurrentHashMap<ScheduleSlot, Program>();

    public Schedule() {
    }

    public Program getProgramById(String id) {
        for (Program p : programsBySlot.values()) {
            if (p.id().equals(id))
                return p;
        }
        throw new ProgramNotFoundException(id);
    }

    public Episode addEpisodeTo(String id, String episodeName, LocalDate programDate, LocalTime localTime,
            int durationInMinutes) {
        Program theProgram = getProgramById(id);
        ScheduleSlot programSlot = theProgram.slot;

        EpisodeTimeSlot episodeTimeSlot = new EpisodeTimeSlot(programDate, localTime, durationInMinutes,
                programSlot.channel);

        for (Program p : programsBySlot.values()) {
            Iterator<Episode> iter = p.getEpisodes();
            while (iter.hasNext()) {
                Episode e = iter.next();
                if (e.conflictsWith(episodeTimeSlot))
                    throw new EpisodeConflictsWithExistingEpisode(e);
            }
        }
        return theProgram.addEpisode(episodeName, programDate, localTime, durationInMinutes);
    }

    public void addEpisodeTo(String id, String episodeName, LocalDate programDate, int durationInMinutes) {
        Program program = getProgramById(id);
        addEpisodeTo(id, episodeName, programDate, program.getScheduleSlot().timeOfDay, durationInMinutes);
    }

    public Program createProgram(String programName, int channel, String dayOfWeek, LocalTime timeOfDay,
            int lengthInMinutes) {
        ScheduleSlot slot = createScheduledSlot(channel, TimeUtil.dayNameToNumber(dayOfWeek), timeOfDay,
                lengthInMinutes);
        Program program = new Program(programName, slot);
        programsBySlot.put(slot, program);
        return program;
    }

    private ScheduleSlot createScheduledSlot(int channel, int dayOfWeek, LocalTime time, int lengthInMinutes) {
        ScheduleSlot slot = new ScheduleSlot(channel, dayOfWeek, time, lengthInMinutes);
        if (programsBySlot.get(slot) != null)
            throw new ScheduleSlotOccupiedException(slot);
        return slot;
    }

    public void clear() {
        programsBySlot.clear();
    }

    @Override
    public Iterator<Program> iterator() {
        return programsBySlot.values().iterator();
    }

    public Iterable<Episode> episodes() {
        List<Episode> allEpisodes = new LinkedList<Episode>();
        for (Program p : programsBySlot.values())
            for (Iterator<Episode> iter = p.getEpisodes(); iter.hasNext();)
                allEpisodes.add(iter.next());

        return allEpisodes;
    }

    public List<Program> findProgramMatching(int channel, String partialName) {
        List<Program> result = new LinkedList<Program>();

        for (Program p : programsBySlot.values())
            if (p.isOn(channel) && p.getProgramName().startsWith(partialName))
                result.add(p);

        return result;
    }

}
