package com.mycompany.mytests.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapEquality {
    public static void main(String[] args) {
        Map<String, Integer> a = new HashMap<>();
        a.put("aa", 4);
        a.put("bb", 5);
        a.put("cc", 7);

        Map<String, Integer> b = new TreeMap<>();
        b.put("aa", 4);
        b.put("bb", 5);
        b.put("cc", 7);

        Map<String, Integer> c = new HashMap<>();
        c.put("aa", 4);
        c.put("bb", 25);
        c.put("cc", 7);

        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
    }
}
