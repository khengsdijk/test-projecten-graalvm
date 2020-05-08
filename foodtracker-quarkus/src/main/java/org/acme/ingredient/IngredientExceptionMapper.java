package org.acme.ingredient;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IngredientExceptionMapper implements ExceptionMapper<IngredientNotFoundException> {

    @Override
    public Response toResponse(IngredientNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}