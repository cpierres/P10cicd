package com.openclassrooms.bobapp.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openclassrooms.bobapp.data.JsonReader;
import com.openclassrooms.bobapp.model.Joke;

@Service
public class JokeService {

    private static final Logger logger = LoggerFactory.getLogger(JokeService.class);
    private final JsonReader jsonReader;

    JokeService(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public Joke getRandomJoke() {
        List<Joke> jokes = this.jsonReader.getJokes();
        Random generator = new Random();
        int randomIndex = generator.nextInt(jokes.size());
        logger.info("Retourne numéro de la blague: {}", randomIndex);
        return jokes.get(randomIndex);
    }

    // méthode avec plusieurs branches conditionnelles et sans test pour stimuler sonar
    public String complexMethodWithoutTests(int value, String input) {
        StringBuilder result = new StringBuilder();

        if (value > 100) {
            result.append("Valeur élevée: ").append(value);
        } else if (value > 50) {
            result.append("Valeur moyenne: ").append(value);
        } else {
            result.append("Valeur faible: ").append(value);
        }

        if (input != null && !input.isEmpty()) {
            if (input.length() > 10) {
                result.append(" - Texte long");
            } else {
                result.append(" - Texte court");
            }

            if (input.matches("^[0-9]+$")) {
                result.append(" - Numérique");
            } else if (input.matches("^[a-zA-Z]+$")) {
                result.append(" - Alphabétique");
            } else {
                result.append(" - Mixte");
            }
        }

        return result.toString();
    }

}