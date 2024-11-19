package com.enslipchaussettes.api.controllers;

import com.enslipchaussettes.api.container.TestPostgreSQLContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class PanierControllersTests {

    @Autowired
    private MockMvc mvc;

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = TestPostgreSQLContainer.getInstance();


    @Test
    public void initPanier() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/panier").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(notNullValue()));
    }

    @Test
    public void savePanier() throws Exception {
        String id = mvc.perform(MockMvcRequestBuilders.post("/panier").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        PanierRequest panierRequest = new PanierRequest();
        panierRequest.setSku("slip-noir");
        String x = new ObjectMapper().writeValueAsString(panierRequest);
        mvc.perform(MockMvcRequestBuilders.put("/panier/"+ id).contentType(MediaType.APPLICATION_JSON)
                                .content(x))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                        .get("/panier/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.articles[0]").value("slip-noir"));
    }
}
