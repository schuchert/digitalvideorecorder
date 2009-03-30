package com.om.dvr.fixture;

import org.joda.time.LocalTime;

import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class TimeOfDayInLocalTimeHandler extends PropertyHandler {
    @Override
    public void handle(PropertyGetter propertyDescriptor, Object targetObject, ObjectDescription objectDescription) {
        LocalTime time = propertyDescriptor.getValue(targetObject, LocalTime.class);
        String value = String.format("%d:%02d", time.getHourOfDay(), time.getMinuteOfHour());
        objectDescription.addPropertyDescription(propertyDescriptor, value);
    }
}
