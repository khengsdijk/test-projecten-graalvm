package org.acme.supplier;

public class SupplierNotFoundException extends RuntimeException {

    public SupplierNotFoundException(Long id){
        super("Could not find supplier with the id: " + id);
    }


}

