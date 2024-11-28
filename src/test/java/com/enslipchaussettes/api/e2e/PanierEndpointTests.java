package com.enslipchaussettes.api.e2e;

import com.enslipchaussettes.api.controllers.panier.AdresseRequest;
import com.enslipchaussettes.api.controllers.panier.PanierRequest;
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

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class PanierEndpointTests {

    @Autowired
    private MockMvc mvc;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");
    // static PostgreSQLContainer postgres = TestPostgreSQLContainer.getInstance();


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

        PanierRequest panierRequest = new PanierRequest("slip-noir");
        //panierRequest.setSku("slip-noir");
        String x = new ObjectMapper().writeValueAsString(panierRequest);
        mvc.perform(MockMvcRequestBuilders.put("/panier/"+ id).contentType(MediaType.APPLICATION_JSON)
                                .content(x))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                        .get("/panier/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.articles[0]").value("slip-noir"));
    }

    @Test
    public void ajouter_une_adresse() throws Exception {
        String id = mvc.perform(MockMvcRequestBuilders.post("/panier").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        PanierRequest panierRequest = new PanierRequest("slip-noir");
        String panierRequestBody = new ObjectMapper().writeValueAsString(panierRequest);
        mvc.perform(MockMvcRequestBuilders.put("/panier/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(panierRequestBody))
                .andExpect(status().isOk());

        AdresseRequest adresseRequest = new AdresseRequest("foobar", "10 rue truc", "75001", "Paris", "France");
        String adresseRequestBody = new ObjectMapper().writeValueAsString(adresseRequest);
        mvc.perform(MockMvcRequestBuilders.put(String.format("/panier/%s/adresse", id)).contentType(MediaType.APPLICATION_JSON)
                        .content(adresseRequestBody))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                        .get("/panier/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.address.name").value("foobar"));

    }

    @Test
    public void valider_panier() throws Exception {
        String id = mvc.perform(MockMvcRequestBuilders.post("/panier").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        PanierRequest panierRequest = new PanierRequest("slip-noir");
        String panierRequestBody = new ObjectMapper().writeValueAsString(panierRequest);
        mvc.perform(MockMvcRequestBuilders.put("/panier/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(panierRequestBody))
                .andExpect(status().isOk());

        AdresseRequest adresseRequest = new AdresseRequest("foobar", "10 rue truc", "75001", "Paris", "France");
        String adresseRequestBody = new ObjectMapper().writeValueAsString(adresseRequest);
        mvc.perform(MockMvcRequestBuilders.put(String.format("/panier/%s/adresse", id)).contentType(MediaType.APPLICATION_JSON)
                        .content(adresseRequestBody))
                .andExpect(status().isOk());


        mvc.perform(MockMvcRequestBuilders.put(String.format("/panier/%s/valider", id)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(notNullValue()));
    }
}
