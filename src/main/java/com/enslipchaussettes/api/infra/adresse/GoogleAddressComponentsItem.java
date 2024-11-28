package com.enslipchaussettes.api.infra.adresse;

import java.util.List;

public record GoogleAddressComponentsItem(String long_name, String short_name, List<String> types) {
}
