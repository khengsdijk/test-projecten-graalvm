package org.acme.product;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.PathVariable;


@Controller("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Product> getAll(){
        return service.findAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product one(@PathVariable Long id){
        return service.findById(id);
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public Product newProduct(@Body Product product){
        return service.SaveProduct(product);
    }

    @Put("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product updateProduct(@Body Product newProduct, @PathVariable Long id){
        return service.updateProduct(newProduct, id);
    }

    @Delete("/{id}")
    public void deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
    }
}
