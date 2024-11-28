package com.enslipchaussettes.api.domain.envoi;

import java.util.UUID;

public interface UtilisationEnvoi {
    void expedierEnvoi(UUID envoiId, String numSuivi);
}
