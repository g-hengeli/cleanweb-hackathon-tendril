package com.tendril.cleanweb.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.tendril.cleanweb.guice.ClientModule;

/**
 */
public class ServletContextListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new DispatchServletModule(), new ClientModule());
    }
}