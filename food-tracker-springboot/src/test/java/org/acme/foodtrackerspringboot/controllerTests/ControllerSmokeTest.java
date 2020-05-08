package org.acme.foodtrackerspringboot.controllerTests;

import org.acme.foodtrackerspringboot.ingredient.IngredientController;
import org.acme.foodtrackerspringboot.product.ProductController;
import org.acme.foodtrackerspringboot.store.StoreController;
import org.acme.foodtrackerspringboot.supplier.SupplierController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControllerSmokeTest {

    @Autowired
    private SupplierController supplierController;

    @Autowired
    private IngredientController ingredientController;

    @Autowired
    private ProductController productController;

    @Autowired
    private StoreController storeController;

    @Test
    public void SupplierContextLoads() throws Exception {
        assertThat(supplierController).isNotNull();
    }

    @Test
    public void IngredientContextLoads() throws Exception {
        assertThat(ingredientController).isNotNull();
    }

    @Test
    public void ProductContextLoads() throws Exception {
        assertThat(productController).isNotNull();
    }

    @Test
    public void StoreContextLoads() throws Exception {
        assertThat(storeController).isNotNull();
    }

}
