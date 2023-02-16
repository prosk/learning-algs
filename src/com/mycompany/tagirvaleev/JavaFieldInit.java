package com.mycompany.tagirvaleev;

public class JavaFieldInit {
    static class MyClass {
        int f1 = Math.abs(56);

        MyClass() {
            // f1 = 12;
        }
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        System.out.println(myClass.f1);
    }
}
