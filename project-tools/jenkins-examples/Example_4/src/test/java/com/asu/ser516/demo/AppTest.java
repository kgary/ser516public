package com.asu.ser516.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void returnsGreetingMessage() {
        App app = new App();
        assertEquals("Hello from SER516 demo", app.getMessage());
    }
}
