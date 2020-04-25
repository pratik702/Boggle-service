package org.assurance.common;

import javax.ws.rs.core.Response;

public class ResponseGen {
    public static Response success(Object entity){
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    public static Response valid(Object entity){
        return Response.status(Response.Status.OK).entity(entity).build();
    }

    public static Response invalid(Object entity){
        return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
    }

    public static Response internalError(Object entity){
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entity).build();
    }
}
