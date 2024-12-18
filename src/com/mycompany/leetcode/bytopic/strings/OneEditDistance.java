package com.mycompany.leetcode.bytopic.strings;

// 161
// https://leetcode.com/problems/one-edit-distance/?envType=company&envId=yandex&favoriteSlug=yandex-three-months

/*
Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.

A string s is said to be one distance apart from a string t if you can:
Insert exactly one character into s to get t.
Delete exactly one character from s to get t.
Replace exactly one character of s with a different character to get t.

Example 1:
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:
Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.
 */

public class OneEditDistance {

    // my solution
    public boolean isOneEditDistance(String s, String t) {
        int diff = s.length() - t.length();
        if (Math.abs(diff) > 1) return false;
        int diffCharsCnt = 0, posS = 0, posT = 0;
        while(posS < s.length() || posT < t.length()) {
            char chS = posS < s.length() ? s.charAt(posS) : ' ';
            char chT = posT < t.length() ? t.charAt(posT) : ' ';
            if (chS == chT || diff == 0) {
                diffCharsCnt += (chS == chT) ? 0 : 1;
                posS++; posT++;
            } else {
                diffCharsCnt++;
                if (s.length() > t.length()) posS++; else posT++;
            }
            if (diffCharsCnt > 1) return false;
        }
        return (posS != 0 || posT != 0) && diffCharsCnt == 1;
    }

    // editorial solution with same logic as my solution but with using substring
    public boolean isOneEditDistanceEd(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // Ensure that s is shorter than t.
        if (ns > nt) return isOneEditDistanceEd(t, s);

        // The strings are NOT one edit away distance
        // if the length diff is more than 1.
        if (nt - ns > 1) return false;

        for (int i = 0; i < ns; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (ns == nt) {
                    // if strings have the same length
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    // If strings have different lengths
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }

        // If there are no diffs in ns distance
        // The strings are one edit away only if
        // t has one more character.
        return (ns + 1 == nt);
    }
}
