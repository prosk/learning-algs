package com.mycompany.leetcode.bytopic.substrings;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/description/
/*
Given a binary string s and a positive integer n, return true if the binary representation
of all the integers in the range [1, n] are substrings of s, or false otherwise.

A substring is a contiguous sequence of characters within a string.
Example 1:

Input: s = "0110", n = 3
Output: true
Example 2:

Input: s = "0110", n = 4
Output: false

Constraints:

1 <= s.length <= 1000
s[i] is either '0' or '1'.
1 <= n <= 10^9

 */
public class SubstringsRepresenting1toN {

    public static void main(String[] args) {
        SubstringsRepresenting1toN runner = new SubstringsRepresenting1toN();
        boolean ans1 = runner.queryString("0110", 3);
        boolean ans2 = runner.queryString("0110", 4);
        System.out.println(ans1 + " " + ans2);
        boolean ans3 = runner.queryStringEd("0110", 3);
        boolean ans4 = runner.queryStringEd("0110", 4);
        System.out.println(ans3 + " " + ans4);
    }

    // my solution O(N * 30 * len(S))
    public boolean queryString(String s, int n) {
        int maxPower = 1;
        while(maxPower*2 <= n) maxPower *= 2;
        for(int i = n; i >= maxPower; i--) {
            String binStr = Integer.toBinaryString(i);
            if (!s.contains(binStr)) return false;
        }
        if (maxPower > 1) {
            int upper = maxPower - 1, lower = maxPower/2;
            for(int i = upper; i >= lower; i--) {
                String binStr = Integer.toBinaryString(i);
                if (!s.contains(binStr)) return false;
            }
        }
        return true;
    }

    // editorial solution O(len(S)*31*31)
    public boolean queryStringEd(String s, int n) {
        // solution O(S*31*31)
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++){
            for (int j = Math.max(i - 30, 0); j <= i; j++){
                int v = 0;

                for (int bit = j; bit <= i; bit++) {
                    v = v * 2 + (s.charAt(bit) - '0');
                }

                if (v > 0 && v <= n) {
                    set.add(v);
                }
            }
        }
        return set.size() == n;
    }
}
