package org.acme.ingredient;

import org.acme.supplier.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository repository;

    private final SupplierService supplierService;

    public IngredientService(IngredientRepository repository, SupplierService supplierService) {
        this.repository = repository;
        this.supplierService = supplierService;
    }

    public Iterable<Ingredient> findAll(){
        return repository.findAll();
    }

    public Ingredient findById(Long id){
        return repository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public Ingredient saveIngredient(Ingredient ingredient){
        // check if supplier exists or throw exception
        supplierService.findById(ingredient.getSupplier().getId());

        return repository.save(ingredient);
    }

    public  Ingredient updateIngredient(Ingredient newIngredient, Long id){

        return repository.findById(id)
                .map(ingredient -> {
                    ingredient.setCalories(newIngredient.getCalories());
                    ingredient.setSupplier(newIngredient.getSupplier());
                    ingredient.setName(newIngredient.getName());

                    return repository.save(ingredient);
                }).orElseGet(() -> {
                    newIngredient.setId(id);
                    return repository.save(newIngredient);
                });
    }

    public void deleteIngredient(Long id){
        repository.deleteById(id);
    }



}
