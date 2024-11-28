package com.enslipchaussettes.api.infra.adresse;

import java.util.List;

public record GoogleAddressComponents(List<GoogleAddressComponentsItem> address_components) {
}