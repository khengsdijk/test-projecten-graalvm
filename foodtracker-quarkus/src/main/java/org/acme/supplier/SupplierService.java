package org.acme.supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Providers;
import java.util.List;

@ApplicationScoped
public class SupplierService {

    @Inject
    SupplierRepository supplierRepository;

    @Context
    private Providers providers;

    public List<Supplier> findAll(){
        return supplierRepository.listAll();
    }

    public Supplier findByID(Long id){
        var supplier = supplierRepository.findByIdOptional(id).orElseThrow(() -> new SupplierNotFoundException(id));
        return supplier;
    }


    @Transactional
    public Supplier saveSupplier(Supplier supplier){
        supplierRepository.persist(supplier);
        return supplier;
    }

    @Transactional
    public Supplier updateSupplier(Long id, Supplier newSupplier){

        return supplierRepository.findByIdOptional(id).map(supplier -> {
            supplier.setCountry(newSupplier.getCountry());
            supplier.setName(newSupplier.getName());
            supplier.setType(newSupplier.getType());
            supplierRepository.persist(supplier);
            return supplier;
        }).orElseGet(() -> {
            newSupplier.setId(id);
            supplierRepository.persist(newSupplier);
            return newSupplier;
        });

    }





}
