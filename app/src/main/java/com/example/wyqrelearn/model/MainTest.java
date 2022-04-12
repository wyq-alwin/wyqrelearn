package com.example.wyqrelearn.model;

public class MainTest {

    public static void test() {
        try {
            throw new IllegalArgumentException();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }
}

