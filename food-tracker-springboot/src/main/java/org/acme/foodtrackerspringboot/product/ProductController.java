package org.acme.foodtrackerspringboot.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/product")
    public List<Product> getAll(){
        return service.findAll();
    }

    @GetMapping("/product/{id}")
    public Product one(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/product")
    public Product newProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product newProduct,@PathVariable Long id){

        return service.updateProduct(newProduct, id);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return "deleted product with id: " + id;
    }
}
