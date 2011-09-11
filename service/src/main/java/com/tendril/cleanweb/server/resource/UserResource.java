package com.tendril.cleanweb.server.resource;

import com.tendril.cleanweb.domain.User;
import com.tendril.cleanweb.domain.tendril.TendrilLocation;
import com.tendril.cleanweb.domain.tendril.TendrilUser;
import com.tendril.cleanweb.server.TendrilClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

/**
 */
@Path("/user")
@Produces("application/json")
public class UserResource {

    private TendrilClient client;

    @Inject
    public void UserResource(TendrilClient client) {
        this.client = client;
    }

    @GET
    public User getUser(@HeaderParam("custom-auth") String auth) throws Exception {
//    public User getUser(@Context HttpHeaders headers) {
        String authValues[] = auth.split(":");
        String username = authValues[0];
        String password = authValues[1];
//        String auth = headers.getRequestHeader("authorization").get(0);
//        String values[] = new String(Base64.base64Decode(auth)).split(":");
//        String username = values[0];

        TendrilUser tendrilUser = client.getUser(username, password);
        TendrilLocation tendrilLocation = client.getLocation(username, password);

        return new User(tendrilUser.getFirstName(), tendrilUser.getLastName(), tendrilUser.getUsername(), tendrilLocation.getPostalCode());
    }

    @GET
    @Path("basic")
    public String foo(@Context javax.ws.rs.core.SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }


    @GET
    @Path("/test")
    public String getUserTest() {
        return "test";
    }
}
