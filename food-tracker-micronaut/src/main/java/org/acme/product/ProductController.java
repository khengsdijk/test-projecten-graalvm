package org.acme.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/product")
    public Iterable<Product> getAll(){
        return service.findAll();
    }

    @GetMapping("/product/{id}")
    public Product one(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/product")
    public Product newProduct(@RequestBody Product product){
        return service.SaveProduct(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product newProduct, Long id){

        return service.updateProduct(newProduct, id);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
    }
}
