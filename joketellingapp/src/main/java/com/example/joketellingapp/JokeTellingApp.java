package com.example.joketellingapp;

import java.util.Arrays;
import java.util.List;

public class JokeTellingApp {

    private static List<String> jokes = Arrays.asList("Joke 1", "Joke 2", "Joke 3", "Joke 4", "Joke 5");

    public static String provideJoke(){
        int selectedJoke = (int) Math.floor(Math.random() * (jokes.size()));
        return jokes.get(selectedJoke);
    }
}
