package com.tendril.cleanweb.server;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.tendril.cleanweb.server.resource.SecurityFilter;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class DispatchServletModule extends ServletModule {

    @Override
    public void configureServlets() {
//        bind(FightResource.class);
//        bind(LeaderboardResource.class);
//        bind(TariffResource.class);
//        bind(UserResource.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        params.put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, LoggingFilter.class.getName() + "," + SecurityFilter.class.getName());
        params.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, LoggingFilter.class.getName());
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES,"com.tendril.cleanweb.server.resource");

        serve("/*").with(GuiceContainer.class, params);
    }

    
}