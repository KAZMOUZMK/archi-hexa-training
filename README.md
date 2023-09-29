# Architecture Hexagonale - TP

Projet Spring Boot d'accompagnement à la formation Architecture Hexagonale

Permet d'avoir toutes les dépendances disponibles pour commencer à coder et tester le projet.

Nécessite de disposer de docker ce.

Pour lancer le projet, commencer par contruire et démarrer la base de données de production et la base de donnée de test.

```shell
$ docker compose up

```

Dans un autre terminal, lancer l'application

```shell
mvnw spring-boot:run
```

Ouvrir ensuite l'URL `http://localhost:8080`
