package com.tendril.cleanweb.server.resource;

import java.security.Principal;

/**
 */
public class SecurityContext implements javax.ws.rs.core.SecurityContext {

    User user;
    private Principal principal;

    public SecurityContext(final User user) {
        this.user = user;
        this.principal = new Principal() {
            @Override
            public String getName() {
                return user.username;
            }
        };
    }

    public String getPassword() {
        return user.password;
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    static class User {
        String username;
        String password;

        User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
