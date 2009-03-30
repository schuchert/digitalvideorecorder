package com.om.dvr.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToDoList {
    List<Episode> scheduledEpisods = new ArrayList<Episode>();

    private int numberOfRecorders;

    public ToDoList() {
        this.numberOfRecorders = 1;
    }

    public ToDoList(int numberOfRecorders) {
        this.numberOfRecorders = numberOfRecorders;
    }

    public void addAll(Iterator<Episode> episodes) {
        while (episodes.hasNext()) {
            Episode e = episodes.next();
            if (!conflictsWithCurrentSchedule(e))
                scheduledEpisods.add(e);
        }
    }

    private boolean conflictsWithCurrentSchedule(Episode episode) {
        int numberOfConflictsAllowed = numberOfRecorders - 1;

        for (Episode scheduled : scheduledEpisods)
            if (episode.hasTimeConflictWith(scheduled)) {
                if (--numberOfConflictsAllowed < 0)
                    return true;
            }

        return numberOfConflictsAllowed < 0;
    }

    public Iterator<Episode> getScheduledEpisods() {
        return scheduledEpisods.iterator();
    }

    public void clear() {
        scheduledEpisods.clear();
    }

    public void setNumberOfRecordes(int numberOfRecorders) {
        this.numberOfRecorders = numberOfRecorders;
    }
}
