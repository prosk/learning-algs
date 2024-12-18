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

    // good public solution
    /*
    * There're 3 possibilities to satisfy one edit distance apart:
    *
    * 1) Replace 1 char:
 	    s: a B c
 	    t: a D c
    * 2) Delete 1 char from s:
	    s: a D  b c
	    t: a    b c
    * 3) Delete 1 char from t
	    s: a   b c
	    t: a D b c
    */
    public boolean isOneEditDistancePublic1(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1));
                else // s is longer than t, so the only possibility is deleting one char from s
                    return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }

    // two pointers + substring
    public boolean isOneEditDistancePublic2(String s, String t) {
        if (s == null || t == null)
            return false;

        if (s.length() > t.length())
            return isOneEditDistancePublic2(t, s);

        int i = 0, j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
                // we try to replace s[i] with s[j] or insert s[j] to s[i]
                // then compare the rest and see if they are the same
                return s.substring(i + 1).equals(t.substring(j + 1)) ||
                    s.substring(i).equals(t.substring(j + 1));
            }

            i++; j++;
        }

        return t.length() - j == 1;
    }

}
