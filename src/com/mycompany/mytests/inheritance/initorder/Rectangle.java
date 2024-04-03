package com.mycompany.mytests.inheritance.initorder;

public class Rectangle extends Shape {

    public Rectangle(String shapeName, int width, int height) {
        super(new StringHolder(shapeName));
        System.out.println("In Rectangle constructor");
        this.width = new IntegerHolder("Constructor rectangle width", width);
        this.height = new IntegerHolder("Constructor rectangle height", height);
    }

    @Override
    void draw() {
        System.out.println("Drawing Rectangle");
    }
    private IntegerHolder width = new IntegerHolder("Rectangle width", 5);
    private IntegerHolder height = new IntegerHolder("Rectangle height", 20);

    private static IntegerHolder rectangleInt1 = new IntegerHolder("Rectangle static 1", 1);
    private static IntegerHolder rectangleInt2 = new IntegerHolder("Rectangle static 2", 2);
}
