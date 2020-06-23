package org.acme.foodtrackerspringboot.store;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @GetMapping("/store")
    public List<Store> getAll(){
        return service.findAll();
    }

    @GetMapping("/store/{id}")
    public Store one(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/store")
    public Store newStore(@RequestBody Store store){
        return service.saveStore(store);
    }

    @PutMapping("/store/{id}")
    public Store updateStore(@RequestBody Store newStore,@PathVariable Long id){

        return service.updateStore(newStore, id);
    }

    @DeleteMapping("/store/{id}")
    public String deleteStore(@PathVariable Long id){
        service.deleteById(id);
        return "deleted store with id: " + id;
    }

}
