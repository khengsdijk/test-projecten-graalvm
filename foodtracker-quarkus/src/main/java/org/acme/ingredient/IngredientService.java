package org.acme.ingredient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Providers;
import java.util.List;

@ApplicationScoped
public class IngredientService {

    @Inject
    IngredientRepository repository;

    @Context
    private Providers providers;

    public List<Ingredient> findAll(){
        return repository.listAll();
    }

    public Ingredient findById(Long id){
        return repository.findByIdOptional(id).orElseThrow(() -> new IngredientNotFoundException(id));
    }

    @Transactional
    public Ingredient saveIngredient(Ingredient ingredient ){
        repository.persist(ingredient);
        return ingredient;
    }

    @Transactional
    public Ingredient updateIngredient(Long id, Ingredient newIngredient){

        return repository.findByIdOptional(id).map(ingredient -> {
            ingredient.setName(newIngredient.getName());
            ingredient.setSupplier(newIngredient.getSupplier());
            ingredient.setCalories(newIngredient.getCalories());
            repository.persist(ingredient);
            return ingredient;
        }).orElseGet(() -> {
            newIngredient.setId(id);
            repository.persist(newIngredient);
            return newIngredient;
        });

    }

}
