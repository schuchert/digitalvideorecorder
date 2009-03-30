package com.om.dvr.fixture;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.om.dvr.domain.Episode;
import com.om.dvr.domain.Program;
import com.om.dvr.domain.SeasonPassManager;
import com.om.dvr.factory.BeanFactory;

public class ThisScheduleGeneratesThese {
    private final ProgramScheduler schduler = new ProgramScheduler();

    public ThisScheduleGeneratesThese(int numberOfRecorders) {
        try {
            getSeasonPassManager().setNumberOfRecorders(numberOfRecorders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(String listOfProgramSearchCriteria) {
        clearAllSeasonPasses();

        for (String program : listOfProgramSearchCriteria.split(","))
            schedule(program);
    }

    private void clearAllSeasonPasses() {
        SeasonPassManager seasonPassManager = getSeasonPassManager();
        seasonPassManager.clear();
    }

    private SeasonPassManager getSeasonPassManager() {
        SeasonPassManager seasonPassManager = BeanFactory.retrieve("seasonPassManager", SeasonPassManager.class);
        return seasonPassManager;
    }

    private void schedule(String channelCommaName) {
        List<Program> programs = getProgramsMatching(channelCommaName);
        for (Program p : programs)
            new CreateSeasonPass(p.id());
    }

    private List<Program> getProgramsMatching(String channelCommaName) {
        String[] parts = channelCommaName.split(":");
        if (parts.length != 2)
            throw new BadlyFormedChannelCommanName(channelCommaName);
        int channel = Integer.parseInt(parts[0]);
        String partialName = parts[1];
        List<Program> programs = schduler.findProgramMatching(channel, partialName);
        return programs;
    }

    public boolean verifyToDoListHasExactly(String listOfEpisodes) {
        List<Episode> episodes = buildExpectedList(listOfEpisodes);

        Iterator<Episode> allEpisodes = getAllEpisodesInToDoList();
        while (allEpisodes.hasNext()) {
            Episode e = allEpisodes.next();
            if (!episodes.remove(e))
                return false;
        }

        return episodes.size() == 0;
    }

    private Iterator<Episode> getAllEpisodesInToDoList() {
        ToDoListContentsFor toDoListContentsFor = new ToDoListContentsFor("");
        Iterator<Episode> allEpisodes = toDoListContentsFor.getScheduledEpisodes();
        return allEpisodes;
    }

    private List<Episode> buildExpectedList(String listOfEpisodes) {
        List<Episode> episodes = new LinkedList<Episode>();

        if (listOfEpisodes.length() > 0) {
            for (String channelCommaName : listOfEpisodes.split(",")) {
                List<Program> programs = getProgramsMatching(channelCommaName);
                for (Program p : programs) {
                    for (Iterator<Episode> iter = p.getEpisodes(); iter.hasNext();)
                        episodes.add(iter.next());
                }
            }
        }

        return episodes;
    }

    public String episodesInToDoList() {
        Iterator<Episode> allEpisodes = getAllEpisodesInToDoList();
        StringBuffer buffer = new StringBuffer();
        while (allEpisodes.hasNext()) {
            Episode e = allEpisodes.next();
            buffer.append(String.format("%d:%s,", e.slot.getChannel(), e.name));
        }
        return buffer.substring(0, Math.max(buffer.length() - 1, 0));
    }
}
