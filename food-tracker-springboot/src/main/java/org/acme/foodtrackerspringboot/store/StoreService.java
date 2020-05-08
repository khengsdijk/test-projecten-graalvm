package org.acme.foodtrackerspringboot.store;

import org.acme.foodtrackerspringboot.ingredient.Ingredient;
import org.acme.foodtrackerspringboot.product.Product;
import org.acme.foodtrackerspringboot.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository repository;

    private final ProductService productService;

    public StoreService(StoreRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public List<Store> findAll(){
        return repository.findAll();
    }

    public Store findById( Long id){
        return repository.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
    }

    public Store saveStore(Store store){

        // check if stores exists
        for(Product product :  store.getProducts()){
            productService.findById(product.getId());
        }

        return repository.save(store);
    }

    public Store updateStore(Store newStore, Long id){

        return repository.findById(id)
                .map(store -> {
                    store.setCountry(newStore.getCountry());
                    store.setName(newStore.getName());
                    return repository.save(store);
                }).orElseGet(() -> {
                    newStore.setId(id);
                    return repository.save(newStore);
                });
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }




}
