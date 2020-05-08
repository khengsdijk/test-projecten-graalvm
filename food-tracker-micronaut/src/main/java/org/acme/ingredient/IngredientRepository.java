package org.acme.ingredient;


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
