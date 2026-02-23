package com.asu.ser516.demo;

public class App {
    public String getMessage() {
        return "Hello from SER516 demo";
    }

    public static void main(String[] args) {
        System.out.println(new App().getMessage());
    }
}
