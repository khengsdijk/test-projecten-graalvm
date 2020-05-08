package org.acme.supplier;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Providers;
import java.util.List;

@Path("supplier")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierResource {

    @Inject
    SupplierService service;

    public List<Supplier> all(){
        return service.findAll();
    }

    @GET
    @Path("{id}")
    public Supplier one(@PathParam("id") Long id){
        return service.findByID(id);
    }

    @POST
    @Transactional
    public Supplier newSupplier(Supplier supplier){
        return service.saveSupplier(supplier);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Supplier updateSupplier(@PathParam("id") Long id, Supplier newSupplier){
       return service.updateSupplier(id, newSupplier);
    }
}
