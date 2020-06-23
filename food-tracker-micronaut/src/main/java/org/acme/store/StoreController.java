package org.acme.store;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.PathVariable;


@Controller("/store")
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Store> getAll(){
        return service.findAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Store one(@PathVariable Long id){
        return service.findById(id);
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public Store newStore(@Body Store store){
        return service.saveStore(store);
    }

    @Put("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Store updateStore(@Body Store newStore, @PathVariable Long id){
        return service.updateStore(newStore, id);
    }

    @Delete("/{id}")
    public void deleteStore(@PathVariable Long id){
        service.deleteById(id);
    }

}
