package com.example.joketellingapp;

import java.util.Arrays;
import java.util.List;

public class JokeTellingApp {

    private static List<String> jokes = Arrays.asList("patata1", "patata2", "patata3", "patata4", "patata5");

    public static String provideJoke(){
        int selectedJoke = (int) Math.floor(Math.random() * (jokes.size()));
        return jokes.get(selectedJoke);
    }
}
