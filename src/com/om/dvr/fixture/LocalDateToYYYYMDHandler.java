package com.om.dvr.fixture;

import org.joda.time.LocalDate;

import com.om.query.domain.ObjectDescription;
import com.om.query.handler.PropertyHandler;
import com.om.reflection.PropertyGetter;

public class LocalDateToYYYYMDHandler extends PropertyHandler {

    @Override
    public void handle(PropertyGetter propertyDescriptor, Object targetObject, ObjectDescription objectDescription) {
        LocalDate date = propertyDescriptor.getValue(targetObject, LocalDate.class);
        String value = String.format("%d/%d/%d", date.getYear(), date.getMonthOfYear(), date.getDayOfMonth());
        objectDescription.addPropertyDescription(propertyDescriptor, value);
    }

}
