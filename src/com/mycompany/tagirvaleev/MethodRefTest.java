package com.mycompany.tagirvaleev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodRefTest {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>(Arrays.asList(23, 12, 67, 45, 89));

        // в одном случае ссылка на метод в другом случае выражение x -> x + 10
        // и там и там генерируется вызов через invoke dynamic
        // но для выражения дополнительно в байткоде гененирируется private static
        // метод, содержащий код лямбды
        long v1 = myList.stream()
                .map(Integer::bitCount)
                .count();

        long v2 = myList.stream()
                .map(x -> x + 10)
                .count();
    }
}
