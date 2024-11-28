package com.enslipchaussettes.api.domain.panier;

import java.util.List;

public interface PanierPresenter {
    void ajouterContenu(List<String> strings);
    void ajouterAdresse(Adresse adresse);
}
