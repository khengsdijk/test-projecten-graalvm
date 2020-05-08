package org.acme.store;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoreRepository implements PanacheRepository<Store> {
}
