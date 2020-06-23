package org.acme.store;

import org.acme.product.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String country;

    @OneToMany
    private List<Product> products;

    public Store() {
    }

    public Store(String name, String country, List<Product> products) {
        this.name = name;
        this.country = country;
        this.products = products;
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
