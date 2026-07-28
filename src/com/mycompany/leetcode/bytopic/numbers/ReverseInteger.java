package com.mycompany.leetcode.bytopic.numbers;

public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        int res = reverseInteger.reverse(1534236469);
        System.out.println(res);
    }
    public int reverse(int x) {
        int reversed = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            // if (digit == 0 && reversed == 0) continue;
            // пытаемся понять, будет ли переполнение на выражении reversed * 10 + digit
            // Integer.MIN_VALUE = -2 147 483 648, / 10 = -214 748 364
            // Integer.MAX_VALUE = 2 147 483 647, / 10 = 214 748 364
            if (reversed < Integer.MIN_VALUE / 10 ||
                    (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            if (reversed > Integer.MAX_VALUE / 10 ||
                    (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            reversed = reversed * 10 + digit;
        }
        return reversed;
    }
}
