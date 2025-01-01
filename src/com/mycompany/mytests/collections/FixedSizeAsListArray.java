package com.mycompany.mytests.collections;

import java.util.Arrays;
import java.util.List;

public class FixedSizeAsListArray {
    public static void main(String[] args) {
        // asList invoke constructor of private static class in Arrays.ArrayList<E>
        // private static class ArrayList<E> extends AbstractList<E>
        //    implements RandomAccess, java.io.Serializable
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.add(4); // java.lang.UnsupportedOperationException
        list.set(1, 55); // list is modifiable
        System.out.println(list);
    }
}
