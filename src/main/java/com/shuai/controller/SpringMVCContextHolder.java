package com.shuai.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringMVCContextHolder implements ApplicationContextAware {


    private  static ApplicationContext context = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringMVCContextHolder.context= applicationContext;

    }

    public static Object getBean(String id) {
        return context.getBean(id);
    }


}
