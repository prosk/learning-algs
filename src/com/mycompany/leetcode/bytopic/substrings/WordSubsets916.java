package com.mycompany.leetcode.bytopic.substrings;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/word-subsets/?envType=problem-list-v2&envId=string&difficulty=MEDIUM
/*
You are given two string arrays words1 and words2.

A string b is a subset of string a if every letter in b occurs in a including multiplicity.

For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.

Return an array of all the universal strings in words1. You may return the answer in any order.

Example 1:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
Output: ["apple","google","leetcode"]


Constraints:

1 <= words1.length, words2.length <= 10^4
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.
 */
public class WordSubsets916 {

    public static void main(String[] args) {
        WordSubsets916 runner = new WordSubsets916();
        String[] words1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2 = {"oo","e", "gg"};
        List<String> ans = runner.wordSubsets(words1, words2);
        System.out.println(ans);
    }

    // my solution - almost same as in editorial
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] maxFreq = new int[26];
        for(String s: words2) {
            int[] chars = getCharsFreq(s);
            updateToMaxFreq(chars, maxFreq);
        }
        List<String> ans = new ArrayList<>();
        for(String s: words1) {
            int[] chars = getCharsFreq(s);
            if (isSubstr(maxFreq, chars)) {
                ans.add(s);
            }
        }
        return ans;
    }

    // another implementation for cycle is
    //   for (char c: s.toCharArray())
    //      chars[c - 'a']++;
    private int[] getCharsFreq(String s) {
        int[] chars = new int[26];
        for(int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }
        return chars;
    }

    private void updateToMaxFreq(int[] src, int[] dst) {
        for(int i = 0; i < 26; i++) {
            dst[i] = Math.max(dst[i], src[i]);
        }
    }

    private boolean isSubstr(int[] sub, int[] s) {
        for(int i = 0; i < 26; i++) {
            if (s[i] < sub[i]) return false;
        }
        return true;
    }
}
