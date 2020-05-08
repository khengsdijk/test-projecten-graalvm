package org.acme.product;



import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Providers;
import java.util.List;

@Path("product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @GET
    public List<Product> all(){
        return service.findAll();
    }

    @GET
    @Path("{id}")
    public Product one(@PathParam("id") Long id){
        return service.findByID(id);
    }

    @POST
    @Transactional
    public Product newManufacturer(Product product ){
        return service.saveManufacturer(product);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Product updateProduct(@PathParam("id") Long id, Product newProduct){
        return service.updateProduct(id, newProduct);
    }

}
