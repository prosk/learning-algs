package com.mycompany.mytests.generics;

import java.util.*;

public class TypeConstructorAsTypeParameter {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>();
        MyBuilder<Integer, List<Integer>> builder = new MyBuilder<>(myList);
        myList = (List<Integer>) builder.add(1).add(234).add(-7).add(45).build();
        System.out.println(myList);

        Collection<String> strList = new ArrayList<>();
        MyBuilder<String, Collection<String>> strBuilder = new MyBuilder<>(strList);
        strList = strBuilder.add("asd").add("qwe").add("zxcv").add("tyutyu").build();
        System.out.println(strList);
    }

    private static class MyBuilder<E, T extends Collection<E>> {
        private Collection<E> collection;

        public MyBuilder(Collection<E> init) {
            this.collection = init;
        }

        public MyBuilder<E, T> add(E elem) {
            collection.add(elem);
            return this;
        }

        public Collection<E> build() {
            return collection;
        }
    }
}
