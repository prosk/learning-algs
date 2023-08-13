package com.mycompany.tagirvaleev;

public class JavaNumbers {
    public static void main(String[] args) {
        // Пример из лекции Тагира Валеева
        // https://www.youtube.com/watch?v=wkHU5akk2po&list=PLlb7e2G7aSpTCB2OxGlezpgOXwq4xer7Z&index=2

        double a = Long.MAX_VALUE;
        double aMin = Long.MIN_VALUE;
        long b = Long.MAX_VALUE;
        int c = 1;

        System.out.printf("%f\n%f\n%d\n%d\n\n", a, aMin, b, c);

        System.out.println(a+b+c);
        System.out.println((a+b)+c);
        System.out.println(a*2);
        System.out.println(a+b);

        System.out.println(c+b+a);
        System.out.println((c+b)+a);

        // num1 остается равной нулю
        // пример из лекции Тагира Валеева https://www.youtube.com/watch?v=xPzIN6Tt1xU&list=PLlb7e2G7aSpTCB2OxGlezpgOXwq4xer7Z&index=3
        int num1 = 0;
        int num2 = 0;
        for(int i = 0; i < 100; i++) {
            num1 = num1++;
            num2 = ++num2;
        }
        System.out.println("num1: " + num1 + " num2 = " + num2);
    }
}
