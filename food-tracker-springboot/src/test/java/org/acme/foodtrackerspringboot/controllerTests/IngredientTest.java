package org.acme.foodtrackerspringboot.controllerTests;

import org.acme.foodtrackerspringboot.ingredient.Ingredient;
import org.acme.foodtrackerspringboot.ingredient.IngredientController;
import org.acme.foodtrackerspringboot.ingredient.IngredientService;
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

@WebMvcTest(IngredientController.class)
public class IngredientTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientService service;

    private Ingredient ingredient;

    @BeforeEach
    public void setup(){
        Supplier supplier = new Supplier("test", "country", "farm" );
        supplier.setId((long) 1);
        
        ingredient = new Ingredient("test", (double) 100, supplier );
        ingredient.setId((long) 2);
    }
    @AfterEach
    public void tearDown(){
        ingredient = null;
    }

    @Test
    public void ingredientShouldReturnJsonList() throws Exception {

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient);

        String expectedJson = "[{\"id\":2,\"name\":\"test\",\"calories\":100.0,\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}]";
        when(service.findAll()).thenReturn(ingredientList);

        this.mockMvc.perform(get("/ingredient"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(expectedJson)))
                .andReturn();
    }

    @Test
    public void ingredientShouldReturnSingleInngredient() throws Exception {

        String expectedJson = "{\"id\":2,\"name\":\"test\",\"calories\":100.0,\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}";
        when(service.findById((long) 1)).thenReturn(ingredient);

        this.mockMvc.perform(get("/ingredient/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(expectedJson)))
                .andReturn();
    }

    @Test
    public void ingredientShouldSave() throws Exception {
        String postContent = "{\"id\":2,\"name\":\"test\",\"calories\":100.0,\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}";

        when(service.saveIngredient(any(Ingredient.class))).thenReturn(ingredient);

        this.mockMvc.perform(post("/ingredient")
                .characterEncoding("utf-8")
                .content(postContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(postContent)))
                .andReturn();
    }

    @Test
    public void ingredientShouldUpdate() throws Exception {
        String updateContent = "{\"id\":2,\"name\":\"name2\",\"calories\":101.2," +
                "\"supplier\":{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}}";


        ingredient.setCalories(101.2);
        ingredient.setName("name2");

        when(service.updateIngredient(any(Ingredient.class), eq((long) 1))).thenReturn(ingredient);

        this.mockMvc.perform(put("/ingredient/1")
                .characterEncoding("utf-8")
                .content(updateContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(updateContent)))
                .andReturn();
    }

    @Test
    public void ingredientShouldDeleteSingleIngredient() throws Exception {
        this.mockMvc.perform(delete("/ingredient/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
