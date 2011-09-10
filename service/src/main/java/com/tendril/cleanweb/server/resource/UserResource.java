package com.tendril.cleanweb.server.resource;

import com.tendril.cleanweb.domain.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 */
@Path("/user")
@Produces("application/json")
public class UserResource {

    @GET
    public User getUser() {
        return new User("fname", "lname", "80023");
    }

    @GET
    @Path("/test")
    public String getUserTest() {
        return "test";
    }
}
