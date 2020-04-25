package org.assurance.common;

import javax.ws.rs.core.Response;

public class ResponseGen {
    public static Response success(Object entity){
        return Response.status(Response.Status.OK).entity(entity).build();
    }
}
