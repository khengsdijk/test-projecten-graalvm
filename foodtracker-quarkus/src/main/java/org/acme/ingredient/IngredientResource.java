package org.acme.ingredient;



import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Providers;
import java.util.List;

@Path("ingredient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IngredientResource {

    @Inject
    IngredientService service;

    @GET
    public List<Ingredient> all(){
        return service.findAll();
    }

    @GET
    @Path("{id}")
    public Ingredient one(@PathParam("id") Long id){
       return service.findById(id);
    }

    @POST
    @Transactional
    public Ingredient newIngredient(Ingredient ingredient ){
        return service.saveIngredient(ingredient);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Ingredient updateIngredient(@PathParam("id") Long id, Ingredient newIngredient){
        return service.updateIngredient(id, newIngredient);
    }
    
}
