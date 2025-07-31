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
        // Erreur de syntaxe intentionnelle : point-virgule manquant
        String test = "Erreur de syntaxe intentionnelle"
        logger.info("Retourne numéro de la blague: {}", randomIndex);
        return jokes.get(randomIndex);
    }
}