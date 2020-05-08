package org.acme.supplier;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SupplierExceptionMapper implements ExceptionMapper<SupplierNotFoundException> {

    @Override
    public Response toResponse(SupplierNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
