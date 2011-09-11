package com.tendril.cleanweb.server.resource;

import com.tendril.cleanweb.domain.User;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 */
@Path("/user")
@Produces("application/json")
public class UserResource {

    @GET
    public User getUser(@HeaderParam("custom-auth") String auth) {
//    public User getUser(@Context HttpHeaders headers) {
        String username = auth.substring(0, auth.indexOf(":"));
//        String auth = headers.getRequestHeader("authorization").get(0);
//        String values[] = new String(Base64.base64Decode(auth)).split(":");
//        String username = values[0];

        return new User("fname", "lname", username, "80023");
    }

    @GET
    @Path("/test")
    public String getUserTest() {
        return "test";
    }
}
