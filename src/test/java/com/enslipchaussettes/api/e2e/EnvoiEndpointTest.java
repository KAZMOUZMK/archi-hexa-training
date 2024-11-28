package com.enslipchaussettes.api.e2e;

import com.enslipchaussettes.api.controllers.envoi.ExpeditionRequest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class EnvoiEndpointTest {
    @Autowired
    private MockMvc mvc;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @Test
    public void valide_un_envoi() throws Exception {
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


        var result = mvc.perform(MockMvcRequestBuilders.put(String.format("/panier/%s/valider", id)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        var envoiId = result.getResponse().getContentAsString();
        var envoiRequest = new ExpeditionRequest("numSuivi");
        mvc.perform(MockMvcRequestBuilders.put(String.format("/envoi/%s/expedier", envoiId)).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(envoiRequest)))
                .andExpect(status().isOk());


        mvc.perform(MockMvcRequestBuilders.get(String.format("/envoi/%s", envoiId)).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(envoiRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroSuivi").value("numSuivi"));
    }
}
