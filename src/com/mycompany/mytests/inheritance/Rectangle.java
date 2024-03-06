package com.mycompany.mytests.inheritance;

public class Rectangle extends Shape {

    public Rectangle(String description) {
        super(description);
        System.out.println("Rectangle constructor for desc " + description);
    }
    @Override
    void draw() {
        System.out.printf("[I am a Rectangle with desc = %s]\n", getDescription());
    }
}
