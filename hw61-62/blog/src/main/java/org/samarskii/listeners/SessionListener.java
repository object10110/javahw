package org.samarskii.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static AtomicInteger activeSessions;

    public SessionListener() {
        super();
        activeSessions = new AtomicInteger();
    }

    public static int getTotalActiveSession() {
        return activeSessions.get();
    }

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
    }
}
