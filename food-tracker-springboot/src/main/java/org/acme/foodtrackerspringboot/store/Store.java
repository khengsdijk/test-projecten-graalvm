package org.acme.foodtrackerspringboot.store;

import org.acme.foodtrackerspringboot.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long Id;

    private String name;

    private String country;

    @OneToMany
    private List<Product> products;

    protected Store() {
    }

    public Store(String name, String country, List<Product> products) {
        this.name = name;
        this.country = country;
        this.products = products;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
