package org.acme.foodtrackerspringboot.controllerTests;

import org.acme.foodtrackerspringboot.ingredient.Ingredient;
import org.acme.foodtrackerspringboot.product.Product;
import org.acme.foodtrackerspringboot.product.ProductService;
import org.acme.foodtrackerspringboot.store.Store;
import org.acme.foodtrackerspringboot.store.StoreController;
import org.acme.foodtrackerspringboot.store.StoreService;
import org.acme.foodtrackerspringboot.supplier.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreController.class)
public class StoreTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService service;

    private Store store;

    @BeforeEach
    public void setup(){
        Supplier supplier = new Supplier("test", "country", "farm" );
        supplier.setId((long) 1);

        Ingredient ingredient = new Ingredient("testIngredient", (double) 100, supplier );
        ingredient.setId((long) 2);
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient);

        Product product = new Product("testProduct", ingredientList, "fakeBrand", "shrimp" );
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        this.store = new Store("store1", "albania", productList );
        store.setId((long) 5);
    }

    @AfterEach
    public void tearDown(){
        store = null;
    }

    @Test
    public void storeShouldReturnJsonList() throws Exception {

        List<Store> storeList = new ArrayList<>();
        storeList.add(store);

        String expectedJson = "[{\"name\":\"store1\",\"country\":\"albania\"," +
                "\"products\":[{\"id\":null,\"name\":\"testProduct\"," +
                "\"ingredients\":[{\"id\":2,\"name\":\"testIngredient\",\"calories\":100.0," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]," +
                "\"brand\":\"fakeBrand\",\"category\":\"shrimp\"}],\"id\":5}]";

        when(service.findAll()).thenReturn(storeList);

        this.mockMvc.perform(get("/store"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(expectedJson)))
                .andReturn();
    }

    @Test
    public void storeShouldReturnSingleStore() throws Exception {

        String expectedJson = "{\"name\":\"store1\",\"country\":\"albania\"," +
                "\"products\":[{\"id\":null,\"name\":\"testProduct\"," +
                "\"ingredients\":[{\"id\":2,\"name\":\"testIngredient\",\"calories\":100.0," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]," +
                "\"brand\":\"fakeBrand\",\"category\":\"shrimp\"}],\"id\":5}";

        when(service.findById((long) 1)).thenReturn(store);

        this.mockMvc.perform(get("/store/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(expectedJson)))
                .andReturn();
    }

    @Test
    public void storeShouldSave() throws Exception {

        String postContent = "{\"name\":\"store1\",\"country\":\"albania\"," +
                "\"products\":[{\"id\":null,\"name\":\"testProduct\"," +
                "\"ingredients\":[{\"id\":2,\"name\":\"testIngredient\",\"calories\":100.0," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]," +
                "\"brand\":\"fakeBrand\",\"category\":\"shrimp\"}],\"id\":5}";

        when(service.saveStore(any(Store.class))).thenReturn(store);

        this.mockMvc.perform(post("/store")
                .characterEncoding("utf-8")
                .content(postContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(postContent)))
                .andReturn();
    }

    @Test
    public void storeShouldUpdate() throws Exception {

        String updateContent = "{\"name\":\"newStore\",\"country\":\"Austria\"," +
                "\"products\":[{\"id\":null,\"name\":\"testProduct\"," +
                "\"ingredients\":[{\"id\":2,\"name\":\"testIngredient\",\"calories\":100.0," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]," +
                "\"brand\":\"fakeBrand\",\"category\":\"shrimp\"}],\"id\":5}";

        store.setName("newStore");
        store.setCountry("Austria");

        when(service.updateStore(any(Store.class), eq((long) 1))).thenReturn(store);

        this.mockMvc.perform(put("/store/1")
                .characterEncoding("utf-8")
                .content(updateContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(updateContent)))
                .andReturn();
    }

    @Test
    public void storeShouldDeleteSingleProduct() throws Exception {
        this.mockMvc.perform(delete("/store/1"))
                .andExpect(status().isOk())
                .andReturn();
    }




}
