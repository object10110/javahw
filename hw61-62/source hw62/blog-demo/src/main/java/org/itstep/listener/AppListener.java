package org.itstep.listener;


import org.itstep.servlet.HomeServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

/*
    События
        Request:
            ServletRequestEvent
            ServletRequestAttributeEvent

        Context:
            ServletContextEvent
            ServletContextAttributeEvent

        Session:
            HttpSessionEvent

    Слушатели событий
        Request:
            ServletRequestListener
            ServletRequestAttributeListener
        Context
            ServletContextListener
            ServletContextAttributeListener
        Session
            HttpSessionListener
            HttpSessionAttributeListener
 */

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("start", LocalDateTime.now());

        ServletRegistration.Dynamic servletRegistration =
                servletContext.addServlet("home", HomeServlet.class); // name
        servletRegistration.setLoadOnStartup(1); // loadOnStartup
        servletRegistration.setInitParameter("user", "admin"); // WebInitParam
        servletRegistration.addMapping("/"); // urlPatterns

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
