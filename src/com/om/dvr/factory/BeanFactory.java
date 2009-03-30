package com.om.dvr.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class BeanFactory {
    private static Map<String, Object> registeredBeans;
    
    static {
        clear();
    }

    public static <T> T retrieve(String beanName, Class<T> clazz) {
        Object found = registeredBeans.get(beanName);
        if (found == null) 
            found = create(beanName, clazz);
        
        return clazz.cast(registeredBeans.get(beanName));
    }
    
    private static <T> T create(String beanName, Class<T> clazz) {
        try {
            T instance = clazz.newInstance();
            registeredBeans.put(beanName, instance);
            return instance;
        } catch (Exception e) {
            throw new CannotCreateBeanInstanceException(beanName, clazz);
        }
    }

    public static void clear() {
        registeredBeans = new ConcurrentHashMap<String, Object>();
    }

}
