package ru.javawebinar.topjava.listeners;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class MyHttpSessionActivationListener implements HttpSessionActivationListener {

    public MyHttpSessionActivationListener() {
        System.out.println("MyHttpSessionActivationListener constructed");
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("Http session was passivated");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("Http session was activated");
    }
}
