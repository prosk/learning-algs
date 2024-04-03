package com.mycompany.mytests.inheritance.initorder;

public abstract class Shape {
    public StringHolder shapeName = new StringHolder("Shape Name");
    public StringHolder shapeDesc = new StringHolder("Shape Description");
    private static IntegerHolder shapeInt = new IntegerHolder("Shape static", 1);

    public Shape(StringHolder shapeName) {
        System.out.println("In Shape constructor");
        this.shapeName = shapeName;
    }

    public String initFromShapeName(int num) {
        return shapeName.getS() + "_" + num;
    }

    abstract void draw();
}
