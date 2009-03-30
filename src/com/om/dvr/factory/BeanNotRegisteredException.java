package com.om.dvr.factory;

public class BeanNotRegisteredException extends RuntimeException {

    public BeanNotRegisteredException(String beanName) {
        super(beanName);
    }

    private static final long serialVersionUID = 1L;

}
