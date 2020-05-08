package org.acme.foodtrackerspringboot.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.foodtrackerspringboot.supplier.Supplier;
import org.acme.foodtrackerspringboot.supplier.SupplierController;
import org.acme.foodtrackerspringboot.supplier.SupplierService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupplierController.class)
public class SupplierTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService service;

    private Supplier supplier;

    @BeforeEach
    public void setup(){
        supplier = new Supplier("test", "country", "farm" );
        supplier.setId((long) 1);
    }
    @AfterEach
    public void tearDown(){
        supplier = null;
    }

    @Test
    public void supplierShouldReturnJsonList() throws Exception {

        List<Supplier>  supplierList = new ArrayList<>();
        supplierList.add(supplier);
        String expectedJson = "[{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}]";
        when(service.findAll()).thenReturn(supplierList);

        this.mockMvc.perform(get("/supplier"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(expectedJson)))
                .andReturn();
    }

    @Test
    public void supplierShouldReturnSingleSupplier() throws Exception {

        String expectedJson = "{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}";
        when(service.findById((long) 1)).thenReturn(supplier);

         this.mockMvc.perform(get("/supplier/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(expectedJson)))
                .andReturn();
    }

    @Test
    public void supplierShouldSave() throws Exception {
        String postContent = "{\"id\":1,\"name\":\"test\",\"country\":\"country\",\"type\":\"farm\"}";

        when(service.saveSupplier(any(Supplier.class))).thenReturn(supplier);

        this.mockMvc.perform(post("/supplier")
                .characterEncoding("utf-8")
                .content(postContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(postContent)))
                .andReturn();
    }

    @Test
    public void supplierShouldUpdate() throws Exception {
        String updateContent = "{\"id\":1,\"name\":\"name2\",\"country\":\"country2\",\"type\":\"farm2\"}";

        supplier.setCountry("country2");
        supplier.setName("name2");
        supplier.setType("farm2");

        when(service.updateSupplier(any(Supplier.class), eq((long) 1))).thenReturn(supplier);

        this.mockMvc.perform(put("/supplier/1")
                .characterEncoding("utf-8")
                .content(updateContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(updateContent)))
                .andReturn();
    }

    @Test
    public void supplierShouldDeleteSingleSupplier() throws Exception {
        this.mockMvc.perform(delete("/supplier/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
