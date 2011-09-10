package com.tendril.cleanweb.server;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.tendril.cleanweb.server.resource.TariffResource;
import com.tendril.cleanweb.server.resource.UserResource;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class DispatchServletModule extends ServletModule {

    @Override
    public void configureServlets() {
        bind(TariffResource.class);
        bind(UserResource.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        serve("/*").with(GuiceContainer.class, params);
    }
}
