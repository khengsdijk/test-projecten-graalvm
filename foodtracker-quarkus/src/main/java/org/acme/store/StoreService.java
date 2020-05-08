package org.acme.store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Providers;
import java.util.List;

@ApplicationScoped
public class StoreService {

    @Inject
    StoreRepository repository;

    @Context
    private Providers providers;

    public List<Store> findAll() {
        return repository.listAll();
    }

    public Store findById(Long id) {
        return repository.findByIdOptional(id).orElseThrow(() -> new StoreNotFoundException(id));
    }

    @Transactional
    public Store saveManufacturer(Store store) {
        repository.persist(store);
        return store;
    }

    @Transactional
    public Store updateStore(Long id, Store newStore) {

        return repository.findByIdOptional(id).map(store -> {
            store.setName(newStore.getName());
            store.setCountry(newStore.getCountry());
            store.setProducts(newStore.getProducts());
            repository.persist(store);
            return store;
        }).orElseGet(() -> {
            newStore.setId(id);
            repository.persist(newStore);
            return newStore;
        });

    }

}
