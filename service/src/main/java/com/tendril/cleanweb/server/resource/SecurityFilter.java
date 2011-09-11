package com.tendril.cleanweb.server.resource;

import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.ws.rs.WebApplicationException;

/**
 */
public class SecurityFilter implements ContainerRequestFilter {

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        SecurityContext securityContext = extractCreds(request);
        request.setSecurityContext(securityContext);

        InBoundHeaders headers = new InBoundHeaders();
        headers.add("custom-auth", securityContext.getUserPrincipal().getName() + ":" + securityContext.getPassword());
        request.setHeaders(headers);
        
        return request;
    }

    private SecurityContext extractCreds(ContainerRequest request) {
        String authentication = request.getHeaderValue(ContainerRequest.AUTHORIZATION);

        authentication = authentication.substring("Basic ".length());
        String[] values = new String(Base64.base64Decode(authentication)).split(":");
        if (values.length < 2) {
            throw new WebApplicationException(400);
            // "Invalid syntax for username and password"
        }
        String username = values[0];
        String password = values[1];
        if ((username == null) || (password == null)) {
            throw new WebApplicationException(400);
            // "Missing username or password"
        }

        return new SecurityContext(new SecurityContext.User(username, password));
    }
}
