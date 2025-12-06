package com.mycompany.tagirvaleev;

import java.util.function.Function;

public class LambdaIdentity {
    public static int CNT = 15;

    public static void main(String[] args) {
        Function<?, ?>[] labmdas = new Function<?, ?>[CNT];

        for(int i = 0; i < CNT; i++) {
            //int n = 25;
            labmdas[i] = (Function<Integer, Integer>) (Integer a) -> a + 10;
            System.out.println(labmdas[i] + " of class " + labmdas[i].getClass());
        }

        Function<Integer, Integer> plusTen = x ->  x + 10;
        Function<Integer, Integer> plusFive = x ->  x + 5;

        System.out.println("plusTen"); // здесь уже другой ID объекта и другой класс, хотя тоже прибавление 10-ти
        System.out.println(plusTen + " of class " + plusTen.getClass());

        System.out.println("plusFive");
        System.out.println(plusFive + " of class " + plusFive.getClass());
        System.out.println("plusFive hashCode = " + plusFive.hashCode());
        System.out.println(labmdas[0].equals(plusTen));

        int res = ((Function<Integer, Integer>) labmdas[0]).apply(25);
        System.out.println("res = " + res);
    }
}
