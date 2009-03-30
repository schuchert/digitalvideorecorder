package com.om.dvr.domain;

public class ScheduleSlotOccupiedException extends RuntimeException {

    public final ScheduleSlot slot;

    public ScheduleSlotOccupiedException(ScheduleSlot slot) {
        super(String.format("Schedule Slot: %s Is Occupied", slot));
        this.slot = slot;
    }

    private static final long serialVersionUID = 1L;

}
