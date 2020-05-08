package org.acme.product;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id){
        super("could not find product with the id: " + id);
    }

}