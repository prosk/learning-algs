package com.mycompany.mytests.inheritance.initorder;

public class ColorRectangle extends Rectangle {
    private static IntegerHolder rectangleInt1 = new IntegerHolder("Color Rectangle static 1", 1);
    private static IntegerHolder rectangleInt2 = new IntegerHolder("Color Rectangle static 2", 2);

    public ColorRectangle(String shapeName, int width, int height, int color) {
        super(shapeName, width, height);
        System.out.println("In ColorRectangle constructor");
        this.firstColor = new IntegerHolder("Constructor firstColor", color);
        this.secondColor = new IntegerHolder("Constructor secondColor", color*10);
    }

    private IntegerHolder firstColor = new IntegerHolder("Rectangle firstColor", 1);
    private IntegerHolder secondColor = new IntegerHolder("Rectangle secondColor", 2);

    public StringHolder shapeName1 = new StringHolder(initFromShapeName(10));
    public StringHolder shapeName2 = new StringHolder(initFromShapeName(20));
}
