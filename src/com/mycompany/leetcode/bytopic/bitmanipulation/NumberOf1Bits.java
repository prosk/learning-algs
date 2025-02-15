package com.mycompany.leetcode.bytopic.bitmanipulation;

// https://leetcode.com/problems/number-of-1-bits/description/
// решения ниже работают для любого целого n и положительного и отрицательного
// чтобы get1BitsCnt работало для отрицательных используется сдвиг >>>
public class NumberOf1Bits {

    public static void main(String[] args) {
        System.out.println("-8 =  " + Integer.toBinaryString(-8));
        System.out.println("-9 =  " + Integer.toBinaryString(-9));
        System.out.println("-10 = " + Integer.toBinaryString(-10));

        //System.out.println(get1BitsCnt(11));
        System.out.println(get1BitsCnt(-4569234));
        System.out.println(get1BitsCntOpt(-4569234));
        System.out.println(Integer.toBinaryString(-4569234));
    }

    // brute force O(countOfBits)
    private static int get1BitsCnt(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += (n & 1);
            n = n >>> 1;
        }
        return cnt;
    }

    // optimal O(countOf1Bits)
    private static int get1BitsCntOpt(int n) {
        int cnt = 0;
        while (n != 0) {
            System.out.println(Integer.toBinaryString(n));
            n = n & (n - 1);
            cnt++;
        }
        return cnt;
    }
}
