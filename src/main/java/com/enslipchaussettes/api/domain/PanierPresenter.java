package com.enslipchaussettes.api.domain;

import java.util.List;

public interface PanierPresenter {
    void ajouterContenu(List<String> strings);

    void afficher();
}
