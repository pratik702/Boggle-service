package org.assurance.application;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext cres) throws IOException {
		String devOrigin = requestContext.getHeaderString("Origin");
		devOrigin = (devOrigin != null && !devOrigin.isEmpty())?devOrigin:"http://localhost:3000";
		cres.getHeaders().add("Access-Control-Allow-Origin", devOrigin);
		cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, X-XSRF-TOKEN, Cookies, Cookie,Set-Cookie");
		cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
		cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS, DELETE");
		cres.getHeaders().add("Access-Control-Max-Age", "1209600");
		cres.getHeaders().add("Access-Control-Expose-Headers", "X-Set-Cookie, Set-Cookie");
		cres.getHeaders().add("Cache-Control", "no-cache, no-store, must-revalidate");

		if (requestContext.getMethod().equals("OPTIONS")) {
		    cres.setStatus(HttpServletResponse.SC_OK);
		    return;
        }
	}

}
