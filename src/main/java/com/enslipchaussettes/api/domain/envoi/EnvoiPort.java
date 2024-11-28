package com.enslipchaussettes.api.domain.envoi;

import java.util.UUID;

public class EnvoiPort implements UtilisationEnvoi {
    private final EnvoiRep envoiRepository;

    public EnvoiPort(EnvoiRep repo) {
        this.envoiRepository = repo;
    }

    @Override
    public void expedierEnvoi(UUID envoiId, String numSuivi) {
        var envoi = envoiRepository.recupererEnvoi(envoiId);
        envoi.expedie(numSuivi);
        envoiRepository.saveEnvoi(envoi);
    };
}
