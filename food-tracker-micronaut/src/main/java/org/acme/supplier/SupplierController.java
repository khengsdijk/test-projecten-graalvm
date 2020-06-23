package org.acme.supplier;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.PathVariable;

@Controller("/supplier")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Supplier> getAll(){
        return service.findAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Error(exception = SupplierNotfoundException.class)
    public Supplier one(@PathVariable Long id){
        return service.findById(id);
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier newSupplier(@Body Supplier supplier){
        return service.saveSupplier(supplier);
    }

    @Put("/{id}")
    public Supplier update(@Body Supplier newSupplier, Long id){
        return service.updateSupplier(newSupplier, id);
    }

    @Delete("/{id}")
    public void deleteSupplier(@PathVariable Long id){
        service.deleteSupplier(id);
    }

}
