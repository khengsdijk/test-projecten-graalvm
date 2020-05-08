package org.acme.ingredient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    private  final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }


    @GetMapping("/ingredient")
    public Iterable<Ingredient> getAll(){
        return service.findAll();
    }

    @GetMapping("/ingredient/{id}")
    public Ingredient one(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/ingredient")
    public Ingredient newIngredient(@RequestBody Ingredient ingredient){
        return service.saveIngredient(ingredient);
    }

    @PutMapping("/ingredient/{id}")
    public  Ingredient updateIngredient(@RequestBody Ingredient newIngredient, Long id){

        return service.updateIngredient(newIngredient, id);
    }

    @DeleteMapping("/ingredient/{id}")
    public void deleteIngredient(@PathVariable Long id){
        service.deleteIngredient(id);
    }

}
