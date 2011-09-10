package com.tendril.cleanweb.resource;

import com.tendril.cleanweb.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 */
@Path("user")
public class UserResource {

    @GET
    public User getUser() {
        return new User("fname", "lname", "80023");
    }
}
