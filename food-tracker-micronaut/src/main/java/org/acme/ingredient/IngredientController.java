package org.acme.ingredient;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.PathVariable;

import javax.inject.Inject;


@Controller("/ingredient")
public class IngredientController {

    private  final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Ingredient> getAll(){
        return service.findAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient one(@PathVariable Long id){
        return service.findById(id);
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient newIngredient(@Body Ingredient ingredient){
        return service.saveIngredient(ingredient);
    }

    @Put("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Ingredient updateIngredient(@Body Ingredient newIngredient,@PathVariable Long id){

        return service.updateIngredient(newIngredient, id);
    }

    @Delete("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteIngredient(@PathVariable Long id){
        service.deleteIngredient(id);
    }

}
