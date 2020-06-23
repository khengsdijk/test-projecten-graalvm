package org.acme.product;


import org.acme.ingredient.Ingredient;
import org.acme.ingredient.IngredientService;

import javax.inject.Singleton;

@Singleton
public class ProductService {


    private final ProductRepository repository;

    private final IngredientService ingredientService;

    public ProductService(ProductRepository repository, IngredientService ingredientService){
        this.repository = repository;
        this.ingredientService = ingredientService;
    }

    public Iterable<Product> findAll(){
        return repository.findAll();
    }

    public Product findById( Long id){

        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product SaveProduct(Product product){
        // check if all ingredients exist
        for(Ingredient ingredient :  product.getIngredients()){
            ingredientService.findById(ingredient.getId());
        }

        return repository.save(product);
    }

    public Product updateProduct(Product newProduct, Long id){

        return repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setIngredients(newProduct.getIngredients());
                    product.setBrand(newProduct.getBrand());
                    product.setCategory(newProduct.getCategory());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

}
