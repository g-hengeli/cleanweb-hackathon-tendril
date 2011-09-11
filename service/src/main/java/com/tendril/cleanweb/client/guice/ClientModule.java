package com.tendril.cleanweb.client.guice;

import com.google.inject.AbstractModule;
import com.sun.jersey.api.client.Client;

import javax.inject.Singleton;

/**
 */
public class ClientModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Client.class).toProvider(ClientProvider.class).in(Singleton.class);
    }
}
