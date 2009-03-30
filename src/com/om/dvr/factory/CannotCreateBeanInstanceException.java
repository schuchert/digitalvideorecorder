package com.om.dvr.factory;

public class CannotCreateBeanInstanceException extends RuntimeException {
    public CannotCreateBeanInstanceException(String beanName, Class<?> clazz) {
        super(String.format("Unable to create bean named: %s, of class: %s", beanName, clazz));
    }

    private static final long serialVersionUID = 1L;

}
