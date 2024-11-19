package com.enslipchaussettes.api.e2e;

import com.enslipchaussettes.api.container.TestPostgreSQLContainer;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class AddressEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = TestPostgreSQLContainer.getInstance();

    @Test
    public void peut_faire_une_recherche_d_adresse() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/adresse?search=140 avenue Jean Lolive 93500 pantin")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].adresse").value("140 Av. Jean Lolive, 93500 Pantin, France"))
                .andExpect(jsonPath("$[0].adresseId").value("ChIJTe56hgBt5kcRMtLb4bh_mLE"));
    }
}
