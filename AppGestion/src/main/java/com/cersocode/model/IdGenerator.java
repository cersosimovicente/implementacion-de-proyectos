package com.cersocode.model;

public class IdGenerator {
    private static int counter = 0;
    public static synchronized int generateId() {
        return ++counter;
    }
}
