package com.mycompany.tagirvaleev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

public class LambdaCapture {
    int field = 10;
    static int sField = 15;

    static int x = 5;

    public static void main(String[] args) {
        List<IntSupplier> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            x++;
            int xx = x;
            IntSupplier l1 = () -> xx * xx;
            list.add(l1);
        }
        for (IntSupplier intSupplier : list) {
            System.out.println(intSupplier.getAsInt());
            Class<?> clazz = intSupplier.getClass();
            System.out.println("clazz = " + clazz.getCanonicalName());
            System.out.println("clazz interfaces = " + Arrays.stream(clazz.getInterfaces())
                    .map(Class::getCanonicalName)
                    .collect(Collectors.joining(", ")).toString());
        }

        System.out.println("Run test() method");
        new LambdaCapture().test();
    }

    void test() {
        int var = 5;
        Runnable r1 = () -> System.out.println(var);
        Runnable r2 = () -> System.out.println(field);
        Runnable r3 = () -> System.out.println(sField);
        field = 5;
        sField = 5;
        r1.run();
        r2.run();
        r3.run();
    }
}
