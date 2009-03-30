package com.om.dvr.fixture;

import com.om.dvr.domain.ScheduleSlot;
import com.om.dvr.util.TimeUtil;
import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class ScheduleSlotPropertyInjectorHandler extends PropertyHandler {

    @Override
    public void handle(PropertyGetter propertyDescriptor, Object targetObject, ObjectDescription objectDescription) {
        ScheduleSlot slot = propertyDescriptor.getValue(targetObject, ScheduleSlot.class);
        objectDescription.addPropertyDescription("channel", Integer.toString(slot.channel));
        objectDescription.addPropertyDescription("dayOfWeek", TimeUtil.dayNumberToName(slot.dayOfWeek));
        objectDescription.addPropertyDescription("timeOfDay", String.format("%d:%02d", slot.timeOfDay.getHourOfDay(),
                slot.timeOfDay.getMinuteOfHour()));
        objectDescription.addPropertyDescription("durationInMinutes", Integer.toString(slot.durationInMintues));
    }

}
