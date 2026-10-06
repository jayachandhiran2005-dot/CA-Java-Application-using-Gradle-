package com.example.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void greetsByName() {
        assertEquals("Hello, Asha!", service.greet("Asha"));
    }

    @Test
    void trimsWhitespace() {
        assertEquals("Hello, Asha!", service.greet("  Asha  "));
    }

    @Test
    void fallsBackToWorldWhenBlankOrNull() {
        assertEquals("Hello, World!", service.greet(""));
        assertEquals("Hello, World!", service.greet("   "));
        assertEquals("Hello, World!", service.greet(null));
    }
}
