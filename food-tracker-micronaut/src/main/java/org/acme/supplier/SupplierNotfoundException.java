package org.acme.supplier;

public class SupplierNotfoundException extends RuntimeException {

    public SupplierNotfoundException(Long id){
        super("Could not find supplier with the id: " + id);
    }


}
