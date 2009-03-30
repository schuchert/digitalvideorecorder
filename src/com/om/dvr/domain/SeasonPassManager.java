package com.om.dvr.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SeasonPassManager {
    private final ToDoList toDoList;

    public SeasonPassManager() {
        this(1);
    }
    
    public SeasonPassManager(int numberOfRecorders) {
        toDoList = new ToDoList(numberOfRecorders);
    }

    public SeasonPassManager(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public void addSeasonPassFor(Program program) {
        toDoList.addAll(program.getEpisodes());
    }

    public Iterator<Episode> episodes(String programId) {
        Iterator<Episode> iter = toDoList.getScheduledEpisods();

        List<Episode> episodes = new LinkedList<Episode>();

        while (iter.hasNext()) {
            Episode e = iter.next();
            if (programId.length() == 0 || e.getProgram().id().equals(programId)) {
                episodes.add(e);
            }
        }

        return episodes.iterator();
    }

    public void clear() {
        toDoList.clear();
    }

    public void setNumberOfRecorders(int numberOfRecorders) {
        toDoList.setNumberOfRecordes(numberOfRecorders);
    }
}
