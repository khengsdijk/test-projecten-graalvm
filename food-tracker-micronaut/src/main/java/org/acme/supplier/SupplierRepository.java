package org.acme.supplier;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;


@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {


}
