package org.itstep;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class MyContextServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.debug("Server started at: http://localhost:8080");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application destroyed");
    }
}
