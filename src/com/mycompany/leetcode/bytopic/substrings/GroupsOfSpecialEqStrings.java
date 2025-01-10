package com.mycompany.leetcode.bytopic.substrings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/groups-of-special-equivalent-strings/description/?envType=problem-list-v2&envId=string&difficulty=MEDIUM
/*
You are given an array of strings of the same length words.

In one move, you can swap any two even indexed characters or any two odd indexed characters of
a string words[i].

Two strings words[i] and words[j] are special-equivalent if after any number of moves,
words[i] == words[j].

For example, words[i] = "zzxy" and words[j] = "xyzz" are special-equivalent because we may
make the moves "zzxy" -> "xzzy" -> "xyzz".
A group of special-equivalent strings from words is a non-empty subset of words such that:

Every pair of strings in the group are special equivalent, and
The group is the largest size possible (i.e., there is not a string words[i] not in the group
such that words[i] is special-equivalent to every string in the group).
Return the number of groups of special-equivalent strings from words.



Example 1:

Input: words = ["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
Output: 3
Explanation:
One group is ["abcd", "cdab", "cbad"], since they are all pairwise special equivalent,
and none of the other strings is all pairwise special equivalent to these.
The other two groups are ["xyzz", "zzxy"] and ["zzyx"].
Note that in particular, "zzxy" is not special equivalent to "zzyx".
Example 2:

Input: words = ["abc","acb","bac","bca","cab","cba"]
Output: 3


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 20
words[i] consist of lowercase English letters.
All the strings are of the same length.
 */
public class GroupsOfSpecialEqStrings {

    public static void main(String[] args) {
        GroupsOfSpecialEqStrings runner = new GroupsOfSpecialEqStrings();
        String[] words = {"abcd","cdab","cbad","xyzz","zzxy","zzyx"};
        int ans1 = runner.numSpecialEquivGroups(words);
        int ans2 = runner.numSpecialEquivGroupsBtfl(words);
        System.out.println(ans1 + " " + ans2);
    }

    // my solution
    public int numSpecialEquivGroups(String[] words) {
        Set<String> groups = new HashSet<>();
        for(String s: words) {
            StringBuilder evenCh = new StringBuilder();
            StringBuilder oddCh = new StringBuilder();
            for(int i = 0; i < s.length(); i++) {
                if (i % 2 == 0)
                    evenCh.append(s.charAt(i));
                else
                    oddCh.append(s.charAt(i));
            }
            char[] evenArr = evenCh.toString().toCharArray();
            char[] oddArr = oddCh.toString().toCharArray();
            Arrays.sort(evenArr);
            Arrays.sort(oddArr);
            String group = new String(evenArr) + new String(oddArr);
            groups.add(group);
        }
        return groups.size();
    }

    // editorial solution
    public int numSpecialEquivGroupsBtfl(String[] words) {
        Set<String> seen = new HashSet();
        for (String s: words) {
            int[] freqArr = new int[52];
            for (int i = 0; i < s.length(); i++)
                freqArr[s.charAt(i) - 'a' + 26 * (i % 2)]++;
            seen.add(Arrays.toString(freqArr));
        }
        return seen.size();
    }
}
