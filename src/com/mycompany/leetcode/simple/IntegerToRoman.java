package com.mycompany.leetcode.simple;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/integer-to-roman/
class IntegerToRoman {

    public static void main(String[] args) {
        String res = new IntegerToRoman().intToRomanWithSimpleMap(1994);
        System.out.println(res);
    }

    public String intToRomanWithSimpleMap(int num) {
        StringBuilder res = new StringBuilder("");
        Map<Integer, String> symbols = new HashMap<>();
        symbols.put(1000, "M");
        symbols.put(900, "CM");
        symbols.put(500, "D");
        symbols.put(400, "CD");
        symbols.put(100, "C");
        symbols.put(90, "XC");
        symbols.put(50, "L");
        symbols.put(40, "XL");
        symbols.put(10, "X");
        symbols.put(9, "IX");
        symbols.put(5, "V");
        symbols.put(4, "IV");
        symbols.put(1, "I");

        List<Integer> digits = symbols.keySet().stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for(Integer digit: digits) {
            while(num >= digit) {
                res.append(symbols.get(digit));
                num -= digit;
            }
        }
        return res.toString();
    }

    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder("");
        int[] powersOfTen = new int[]{1000, 100, 10, 1};

        int currValue = num;
        for(int i = 0; i < powersOfTen.length; i++) {
            int q = currValue / powersOfTen[i];
            int r = currValue % powersOfTen[i];
            if (q > 0) {
                if (i == 0) {
                    // тысячи
                    appendNDigits(res, 'M', q);
                } else if (i == 1) {
                    // сотни
                    if (q == 9 || q == 4) {
                        res.append(q == 9 ? "CM" : "CD");
                    } else if (q >= 5) {
                        res.append("D");
                        appendNDigits(res, 'C', q-5);
                    } else {
                        appendNDigits(res, 'C', q);
                    }
                } else if (i == 2) {
                    // десятки
                    if (q == 9 || q == 4) {
                        res.append(q == 9 ? "XC" : "XL");
                    } else if (q >= 5) {
                        res.append("L");
                        appendNDigits(res, 'X', q-5);
                    } else {
                        appendNDigits(res, 'X', q);
                    }
                } else if (i == 3) {
                    // единицы
                    if (q == 9 || q == 4) {
                        res.append(q == 9 ? "IX" : "IV");
                    } else if (q >= 5) {
                        res.append("V");
                        appendNDigits(res, 'I', q-5);
                    } else {
                        appendNDigits(res, 'I', q);
                    }
                }
            }
            currValue = r;
        }
        return res.toString();
    }

    void appendNDigits(StringBuilder sb, char digitChar, int count) {
        for(int j = 0; j < count; j++) {
            sb.append(digitChar);
        }
    }

}
