package org.acme.store;

import org.acme.product.ProductNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StoreExceptionMapper implements ExceptionMapper<StoreNotFoundException> {

    @Override
    public Response toResponse(StoreNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
