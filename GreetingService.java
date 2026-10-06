package com.example.app;

/** Small piece of business logic, kept separate from HTTP code so it is easy to test. */
public class GreetingService {

    public String greet(String name) {
        String who = (name == null || name.isBlank()) ? "World" : name.trim();
        return "Hello, " + who + "!";
    }
}
