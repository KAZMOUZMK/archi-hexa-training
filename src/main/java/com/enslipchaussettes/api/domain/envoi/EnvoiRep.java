package com.enslipchaussettes.api.domain.envoi;

import java.util.UUID;

public interface EnvoiRep {
    void saveEnvoi(Envoi envoi);

    Envoi recupererEnvoi(UUID id);
}
