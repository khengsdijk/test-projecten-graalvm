package org.acme.ingredient;

import org.acme.supplier.Supplier;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;


@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double calories;

    @ManyToOne
    private Supplier supplier;

    public  Ingredient(){}

    public Ingredient(String name, Double calories, Supplier supplier) {
        this.name = name;
        this.calories = calories;
        this.supplier = supplier;
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

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
