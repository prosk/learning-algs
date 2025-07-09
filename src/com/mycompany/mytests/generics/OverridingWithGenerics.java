package com.mycompany.mytests.generics;

public class OverridingWithGenerics {
    public static void main(String[] args) {
        Animal animal = new Dog();
        Food flesh = new Flesh();
        animal.eat(flesh);
    }
}

class Food {
    public String toString() {
        return "Normal Food";
    }
}

class Flesh extends Food {
    @Override
    public String toString() {
        return "Flesh Food";
    }
}

class Animal<T extends Food> {
    public void eat(T food) {
        System.out.println("Animal eats " + food);
    }
}

class Dog extends Animal<Flesh> {
    @Override
    public void eat(Flesh flesh) {
        System.out.println("Dog eats " + flesh);
    }
}