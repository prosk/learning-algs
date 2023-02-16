package com.mycompany.yandex;

import java.util.Scanner;

public class CompareJS {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String J = s.nextLine();
        String S = s.nextLine();

        long res = S.chars().filter(c -> J.chars().anyMatch(j -> j == c)).count();

        System.out.println(res);
    }
}
