package com.mycompany.mytests.inheritance.initorder;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start!");
        ColorRectangle colorRectangle = new ColorRectangle("My color rectangle", 25, 49, 33);
        System.out.println("Shape name = " + colorRectangle.shapeName.getS());
        System.out.println("Shape desc = " + colorRectangle.shapeDesc.getS());
        System.out.println(colorRectangle.shapeName1.getS() + " " + colorRectangle.shapeName2.getS());
        colorRectangle.shapeName = new StringHolder("New_shape_name");
        System.out.println(colorRectangle.shapeName1.getS() + " " + colorRectangle.shapeName2.getS());
        System.out.println("Ok!");
    }
}
