package com.mycompany.mytests.generics;

/*
  It is possible to use the same name for both a generic method type parameter
  and a class type parameter in Java !!!

  How the compiler handles this:
  The method type parameter shadows the class type parameter within the method scope. This means:
  > Inside the method, the type parameter refers to the method's generic type
  > The class type parameter becomes inaccessible within that method
  > Outside the method, the class type parameter remains accessible

  While technically allowed, this practice is not recommended as it can cause
  confusion and make code harder to understand.
  It's better to use distinct names like T for class and U for method parameters.
 */

public class Container<T> {
    private T classField;

    // Method type parameter T shadows class type parameter T
    public <T> void process(T methodParam) {
        // T here refers to the method's type parameter
        T localVar = methodParam; // Valid

        // Cannot access class type parameter T directly
        // incompatible types: T cannot be converted to T
        //this.classField = methodParam; // Compile error
    }

    // Regular method - uses class type parameter T
    public void store(T item) {
        this.classField = item; // T refers to class type parameter
    }

    public static void main(String[] args) {
        Container<String> container = new Container<>();
        container.process(425); // T in process() is Integer
        container.store("hello"); // T in store() is String
        System.out.println("Hello");
    }
}
