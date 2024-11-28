package com.enslipchaussettes.api.domain.adresse;

import java.util.List;

public record GoogleSearchPlaceReturn(List<GoogleSearchPlaceCandidates> candidates, String status) {}
