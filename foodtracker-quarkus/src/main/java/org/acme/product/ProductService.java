package org.acme.product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Providers;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    @Context
    private Providers providers;

    public List<Product> findAll(){
        return repository.listAll();
    }

    public Product findByID(Long id){
        return repository.findByIdOptional(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public Product saveManufacturer(Product product ){
        repository.persist(product);
        return product;
    }

    @Transactional
    public Product updateProduct(Long id, Product newProduct){

        return repository.findByIdOptional(id).map(product -> {
            product.setName(newProduct.getName());
            product.setIngredients(newProduct.getIngredients());
            repository.persist(product);
            return product;
        }).orElseGet(() -> {
            newProduct.setId(id);
            repository.persist(newProduct);
            return newProduct;
        });

    }


}
