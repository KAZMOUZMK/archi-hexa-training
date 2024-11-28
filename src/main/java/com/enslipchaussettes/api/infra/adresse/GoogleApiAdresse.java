package com.enslipchaussettes.api.infra.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import com.enslipchaussettes.api.domain.adresse.AdresseRepository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleApiAdresse implements AdresseRepository {
    private final RestTemplate restTemplate;
    private  String google_place_key = System.getenv("GOOGLE_PLACE_KEY");

    public GoogleApiAdresse() {
        restTemplate = new RestTemplate();
    }
    @Override
    public List<RechercheAdresseResponse> rechercheAdresse(String search) {
        Map<String,String> params = new HashMap<>();
        params.put("key", google_place_key);
        params.put("input", search);

        String apiEndpoint = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key={key}&input={input}&inputtype=textquery&fields=formatted_address,place_id";
        //this.restTemplate.get
        var googleSearchPlaceReturn = this.restTemplate.getForObject( apiEndpoint, GoogleSearchPlaceReturn.class, params );
        //this.restTemplate.ex
        var returned = googleSearchPlaceReturn.candidates().stream().map(c -> new RechercheAdresseResponse(c.place_id(), c.formatted_address())).toList();
        return returned;

    }
}
