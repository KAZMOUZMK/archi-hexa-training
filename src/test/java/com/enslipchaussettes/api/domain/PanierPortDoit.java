package com.enslipchaussettes.api.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PanierPortDoit {
    @Test
    public void sauver_un_panier_initialise() {
        PanierRepDouble rep = new PanierRepDouble();
        PanierRep rep2 = mock(PanierRep.class);
        PanierPort port  = new PanierPort(rep2);

        UUID panierId = port.initPanier();
        verify(rep2).savePanier(argThat(panier -> panier.uuid.equals(panierId)));
        // rep.savePanierAEteApple();
    }

    class PanierRepDouble implements PanierRep {

        private boolean savePanierAppele = false;

        @Override
        public void savePanier(Panier panier) {
            savePanierAppele = true;

        }

        @Override
        public Panier getPanier(UUID uuid) {
            return null;
        }

        public void savePanierAEteApple() {
            assertTrue(savePanierAppele);
        }
    }
}
