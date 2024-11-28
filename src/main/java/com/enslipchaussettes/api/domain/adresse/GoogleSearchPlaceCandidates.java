package com.enslipchaussettes.api.domain.adresse;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleSearchPlaceCandidates(@JsonProperty("place_id") String place_id, @JsonProperty("formatted_address") String formatted_address) {}
