package org.acme.foodtrackerspringboot.product;

import org.acme.foodtrackerspringboot.ingredient.Ingredient;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name="product_id")
    private Long id;

    private String name;

    @OneToMany
    private List<Ingredient> ingredients;

    private String brand;

    private String category;

    protected Product(){}

    public Product(String name, List<Ingredient> ingredients, String brand, String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.brand = brand;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
