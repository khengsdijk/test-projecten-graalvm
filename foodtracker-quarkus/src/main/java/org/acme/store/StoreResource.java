package org.acme.store;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Providers;
import java.util.List;

@Path("store")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StoreResource {

    @Inject
    StoreService service;

    @GET
    public List<Store> all() {
        return service.findAll();
    }

    @GET
    @Path("{id}")
    public Store one(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    public Store newManufacturer(Store store) {
        return service.saveManufacturer(store);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Store updateStore(@PathParam("id") Long id, Store newStore) {
        return service.updateStore(id, newStore);
    }

}