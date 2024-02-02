package com.mycompany.mytests.inheritance;

public class Main {
    public static void main(String[] args) {
        Shape onlyShape = new Shape();
        System.out.println("Draw base shape " + onlyShape.getClass());
        onlyShape.draw();

        Shape shape = new Rectangle();
        System.out.println("Draw rectangle " + shape.getClass());
        shape.draw();

        Object obj = new Rectangle();
        System.out.println("Draw rectangle in Object " + obj.getClass());
        ((Shape)obj).draw();

    }
}
