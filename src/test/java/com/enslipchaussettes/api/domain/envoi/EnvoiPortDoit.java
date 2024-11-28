package com.enslipchaussettes.api.domain.envoi;

import com.enslipchaussettes.api.controllers.panier.AdresseRequest;
import com.enslipchaussettes.api.domain.panier.Panier;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class EnvoiPortDoit {

    @Test
    public void expedier_un_envoi() {
        var panier = new Panier();
        panier.ajouterAdresse(new AdresseRequest("nom", "123 rue toto", "95800", "Courdimanche", "France"));
        var envoi = panier.validerPanier();
        EnvoiRep repo = mock(EnvoiRep.class);
        when(repo.recupererEnvoi(envoi.getId())).thenReturn(envoi);
        EnvoiPort port = new EnvoiPort(repo);
        port.expedierEnvoi(envoi.getId(), "numSuivi");
        verify(repo).saveEnvoi(argThat(e -> e.getNumeroSuivi().equals("numSuivi")));
    }
}
