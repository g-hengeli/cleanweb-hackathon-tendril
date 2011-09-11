package com.tendril.cleanweb.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;
import com.tendril.cleanweb.domain.tendril.TendrilLocation;
import com.tendril.cleanweb.domain.tendril.TendrilUser;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

/**
 */
public class TendrilClient {

    private Client client;

    @Inject
    public TendrilClient(@Named("user-client") Client client) {
        this.client = client;
    }

    public TendrilUser getUser(String username, String password) throws Exception {
//        ClientConfig config = new DefaultClientConfig();
//        SSLContext ctx = SSLContext.getInstance("SSL");
//        ctx.init(null, myTrustManager, null);
//        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(null, ctx));
//        Client c = Client.create(config);
        DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig();
//        config.getState().setCredentials(null, null, 443, username, password);
        Client c = ApacheHttpClient.create(config);
        WebResource webResource = c.resource("https://dev-program.tendrildemo.com/api/rest/user/current-user");
        webResource.header("Emsauthtoken", username + ":" + password);
        TendrilUser user = webResource.type(MediaType.APPLICATION_JSON_TYPE).get(TendrilUser.class);

        return user;
    }

    public TendrilLocation getLocation(String username, String password) {
        client.addFilter(new HTTPBasicAuthFilter(username, password));
        WebResource webResource = client.resource("https://dev-program.tendrildemo.com/api/rest/user/current-user/account/default-account/location/default-location");

        TendrilLocation location = webResource.type(MediaType.APPLICATION_JSON_TYPE).get(TendrilLocation.class);

        return location;
    }

}