package com.tendril.cleanweb.server;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.tendril.cleanweb.server.resource.FightResource;
import com.tendril.cleanweb.server.resource.LeaderboardResource;
import com.tendril.cleanweb.server.resource.SecurityFilter;
import com.tendril.cleanweb.server.resource.TariffResource;
import com.tendril.cleanweb.server.resource.UserResource;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class DispatchServletModule extends ServletModule {

    @Override
    public void configureServlets() {
        bind(FightResource.class);
        bind(LeaderboardResource.class);
        bind(TariffResource.class);
        bind(UserResource.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        params.put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, LoggingFilter.class.getName() + "," + SecurityFilter.class.getName());
        params.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, LoggingFilter.class.getName());

        serve("/*").with(GuiceContainer.class, params);
    }
}