package org.acme.supplier;

import io.micronaut.http.HttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping("/supplier")
    public HttpResponse getAll(){
        return HttpResponse.ok().body(service.findAll());
    }

    @GetMapping("/supplier/{id}")
    public Supplier one(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/supplier")
    public Supplier newSupplier(@RequestBody Supplier supplier){
        return service.saveSupplier(supplier);
    }

    @PutMapping("/supplier/{id}")
    public Supplier update(@RequestBody Supplier newSupplier, Long id){

        return service.updateSupplier(newSupplier, id);
    }

    @DeleteMapping("/supplier/{id}")
    public void deleteSupplier(@PathVariable Long id){
        service.deleteSupplier(id);
    }

}
