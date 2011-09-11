package com.tendril.cleanweb.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.sun.jersey.api.client.Client;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 */
public class ClientModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Named("user-client")
    @Singleton
    public Client getUserClient() {
        Client c = Client.create();

        return c;
    }
}
