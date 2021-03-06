package org.acme.foodtrackerspringboot.supplier;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository){
        this.repository = repository;
    }

    public List<Supplier> findAll(){
        return repository.findAll();
    }

    public Supplier findById( Long id){
        return repository.findById(id).orElseThrow(() -> new SupplierNotfoundException(id));
    }

    public Supplier saveSupplier(Supplier supplier){
        return repository.save(supplier);
    }

    public Supplier updateSupplier(Supplier newSupplier, Long id){

        return repository.findById(id)
                .map(supplier -> {
                    supplier.setCountry(newSupplier.getCountry());
                    supplier.setName(newSupplier.getName());
                    supplier.setType(newSupplier.getType());
                    return repository.save(supplier);
                }).orElseGet(() -> {
                    newSupplier.setId(id);
                    return repository.save(newSupplier);
                });
    }

    public void deleteSupplier(Long id){
        repository.deleteById(id);
    }




}
