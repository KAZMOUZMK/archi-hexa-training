package com.enslipchaussettes.api.infra.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import com.enslipchaussettes.api.controllers.RechercheDetailAdresseResponse;
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

    @Override
    public RechercheDetailAdresseResponse recupererDetail(String placeId) {
        Map<String,String> params = new HashMap<>();
        params.put("place_id", placeId);
        params.put("key", google_place_key);
        String apiEndpoint = "https://maps.googleapis.com/maps/api/place/details/json?key={key}&place_id={place_id}&fields=address_components";
        var googleSearchPlaceReturn = this.restTemplate.getForObject( apiEndpoint, GooglePlaceDetailReturn.class, params );
        String streetNumber ="";
        String streetName="";
        String locality="";
        String postal_code="";
        String country="";
        for (GoogleAddressComponentsItem item : googleSearchPlaceReturn.result().address_components()) {
            if (item.types().contains("street_number")) {
                streetNumber = item.short_name();
            }

            else if (item.types().contains("route")) {
                streetName = item.short_name();
            }

            else if (item.types().contains("locality")) {
                locality = item.long_name();
            }

            else if (item.types().contains("postal_code")) {
                postal_code = item.short_name();
            }

            else if (item.types().contains("country")) {
                country = item.long_name();
            }
        }
        return new RechercheDetailAdresseResponse(
                String.format("%s %s", streetNumber, streetName),
                postal_code,
                locality,
                country);
    }
}
