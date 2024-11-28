package com.enslipchaussettes.api.infra.adresse;

import java.util.List;

public record GoogleSearchPlaceReturn(List<GoogleSearchPlaceCandidates> candidates, String status) {}
