package com.mycompany.mytests.lambdas;
import java.util.function.Function;
public class Lambda {
    String header = "This is a ";
    Function<Object, String> f = obj -> header + obj.toString();
}
