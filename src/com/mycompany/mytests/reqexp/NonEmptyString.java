package com.mycompany.mytests.reqexp;

public class NonEmptyString {
    // "^(?=\\s*\\S).*$"
    // string has at least one non-whitespace character:

    public static void main(String[] args) {
        System.out.println("asdf".matches("^(?=\\s*\\S).*$"));
        System.out.println("".matches("^(?=\\s*\\S).*$"));
        System.out.println(" ".matches("^(?=\\s*\\S).*$"));
        System.out.println("     ".matches("^(?=\\s*\\S).*$"));
        System.out.println("    ,  ".matches("^(?=\\s*\\S).*$"));
    }
}
