package com.mycompany.mytests;

public class IdentifierTest {
    public static void main(String[] args) {
        /*int __ = 123;
        String МояСтрока = "Содержимое моей строки";

        System.out.println("__ = " + __);
        System.out.println("МояСтрока = " + МояСтрока);*/

        Object str1 = "aaa";
        Object str2 = "aaa";
        Object str3 = "bbb";
        System.out.println(str1.equals(str2));
        System.out.println(str1.equals(str3));


        Integer a = 1;
        System.out.println(a == 1);

    }
}
