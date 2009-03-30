package com.om.dvr.fixture;

import com.om.dvr.domain.EpisodeTimeSlot;
import com.om.dvr.util.TimeUtil;
import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class EpisodeTimeSlotHandler extends PropertyHandler {

    @Override
    public void handle(PropertyGetter propertyDescriptor, Object targetObject, ObjectDescription objectDescription) {
        EpisodeTimeSlot slot = propertyDescriptor.getValue(targetObject, EpisodeTimeSlot.class);
        objectDescription.addPropertyDescription("channel", slot.getChannel());
        objectDescription.addPropertyDescription("durationInMinutes", slot.getDurationInMinutes());
        objectDescription.addPropertyDescription("date", TimeUtil.formatDateYMD(slot.getDate()));
        objectDescription.addPropertyDescription("startTime", TimeUtil.formatTimeHMM(slot.getStartTime()));
    }
}
