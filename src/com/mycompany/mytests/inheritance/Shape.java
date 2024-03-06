package com.mycompany.mytests.inheritance;

public class Shape {
    private String description;

    public Shape(String description) {
        System.out.println("Shape constructor for desc " + description);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    void draw() {
        System.out.printf("[I am a base shape with desc = %s]\n", description);
    }
}
