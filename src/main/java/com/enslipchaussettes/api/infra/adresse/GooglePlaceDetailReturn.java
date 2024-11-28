package com.enslipchaussettes.api.infra.adresse;

import java.util.List;

public record GooglePlaceDetailReturn(List<String> html_attributions, String status, GoogleAddressComponents result) {
}
