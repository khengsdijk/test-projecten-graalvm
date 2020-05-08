package org.acme.store;

public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException(Long id){
        super("could not find a store with the id: " + id );
    }
}
