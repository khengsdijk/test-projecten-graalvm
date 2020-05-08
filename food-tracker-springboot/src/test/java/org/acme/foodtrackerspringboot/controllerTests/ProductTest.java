package org.acme.foodtrackerspringboot.controllerTests;

import org.acme.foodtrackerspringboot.ingredient.Ingredient;
import org.acme.foodtrackerspringboot.product.Product;
import org.acme.foodtrackerspringboot.product.ProductController;
import org.acme.foodtrackerspringboot.product.ProductService;
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

@WebMvcTest(ProductController.class)
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    private Product product;

    @BeforeEach
    public void setup(){
        Supplier supplier = new Supplier("test", "country", "farm" );
        supplier.setId((long) 1);

        Ingredient ingredient = new Ingredient("testIngredient", (double) 100, supplier );
        ingredient.setId((long) 2);
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient);

        this.product = new Product("testProduct", ingredientList, "fakeBrand", "shrimp" );

    }
    @AfterEach
    public void tearDown(){
        product = null;
    }

    @Test
    public void productShouldReturnJsonList() throws Exception {

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        String expectedJson = "[{\"id\":null,\"name\":\"testProduct\"," +
                "\"ingredients\":[{\"id\":2,\"name\":\"testIngredient\",\"calories\":100.0," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]," +
                "\"brand\":\"fakeBrand\",\"category\":\"shrimp\"}]";

        when(service.findAll()).thenReturn(productList);

        this.mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(expectedJson)))
                .andReturn();
    }

    @Test
    public void productShouldReturnSingleProduct() throws Exception {

        String expectedJson = "{\"id\":null,\"name\":\"testProduct\"," +
                "\"ingredients\":[{\"id\":2,\"name\":\"testIngredient\",\"calories\":100.0," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]," +
                "\"brand\":\"fakeBrand\",\"category\":\"shrimp\"}";


        when(service.findById((long) 1)).thenReturn(product);

        this.mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(expectedJson)))
                .andReturn();
    }

    @Test
    public void productShouldSave() throws Exception {

        String postContent = "{\"id\":null,\"name\":\"testProduct\"," +
                "\"ingredients\":[{\"id\":2,\"name\":\"testIngredient\",\"calories\":100.0," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]," +
                "\"brand\":\"fakeBrand\",\"category\":\"shrimp\"}";

        when(service.saveProduct(any(Product.class))).thenReturn(product);

        this.mockMvc.perform(post("/product")
                .characterEncoding("utf-8")
                .content(postContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(postContent)))
                .andReturn();
    }

    @Test
    public void productShouldUpdate() throws Exception {

        String updateContent = "{\"id\":null,\"name\":\"milk\"," +
                "\"ingredients\":[{\"id\":2,\"name\":\"testIngredient\",\"calories\":100.0," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]," +
                "\"brand\":\"fakeBrand\",\"category\":\"dairy\"}";

        product.setCategory("dairy");
        product.setName("milk");

        when(service.updateProduct(any(Product.class), eq((long) 1))).thenReturn(product);

        this.mockMvc.perform(put("/product/1")
                .characterEncoding("utf-8")
                .content(updateContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(updateContent)))
                .andReturn();
    }

    @Test
    public void productShouldDeleteSingleProduct() throws Exception {
        this.mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk())
                .andReturn();
    }



}
