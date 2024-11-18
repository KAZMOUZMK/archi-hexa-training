package com.enslipchaussettes.api.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PanierTest {
    @Test
    public void pouvoir_ajouter_une_reference() {
        var p = new Panier();

        p.addReference("ref1");
        var actualContenu = p.showPanier();

        assertNotNull(p.uuid);
        assertEquals(actualContenu.getFirst(), "ref1");
    }

}
