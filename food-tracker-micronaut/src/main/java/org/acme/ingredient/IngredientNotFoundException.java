package org.acme.ingredient;

public class IngredientNotFoundException extends RuntimeException{

    public IngredientNotFoundException(Long id){
        super("could not find ingredient with the id: " + id );
    }

}
