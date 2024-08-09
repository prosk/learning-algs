package com.mycompany.mytests.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsSimpleTest {
    public static void main(String[] args) {
        testParamType();
    }

    private static void testParamType() {
        // public interface List<E> extends Collection<E>
        List<String> myList = new ArrayList<>(Arrays.asList("asdf", "qweqwe", "asdasdas"));
        CharSequence[] arr = myList.toArray(new CharSequence[0]);
        System.out.println(Arrays.toString(arr));

        // E get(int index);
        // compile error
        // java: incompatible types: java.lang.String cannot be converted to java.lang.Integer
        // ошибка компиляции так как метод get возвращает тип E - тип элементов в списке
        // и компилятор знает что в списке myList элементы с типом String
        //Integer elem = myList.get(0);

        // <T> T[] toArray(T[] a);
        // здесь ошибка только в рантайме так как toArray обобщенный метод со своим параметром типа T
        // никаких ограничений на T не задано и компилятор не знает что тип T как-то должен быть
        // связан с типом E
        // это указано только в описании метода - Throws: ArrayStoreException – if the runtime type of the specified array
        // is not a supertype of the runtime type of every element in this list
        // Exception in thread "main" java.lang.ArrayStoreException
        Integer[] errArr = myList.toArray(new Integer[0]);
        System.out.println(Arrays.toString(errArr));
    }
}
