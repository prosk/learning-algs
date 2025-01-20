package com.mycompany.postupashki.roadmap2025jan.prefsums;

// 3361 Shift Distance Between Two Strings
// https://leetcode.com/problems/shift-distance-between-two-strings/description/?envType=problem-list-v2&envId=prefix-sum
/*
You are given two strings s and t of the same length, and two integer arrays nextCost and previousCost.

In one operation, you can pick any index i of s, and perform either one of the following actions:

Shift s[i] to the next letter in the alphabet. If s[i] == 'z', you should replace it with 'a'.
This operation costs nextCost[j] where j is the index of s[i] in the alphabet.

Shift s[i] to the previous letter in the alphabet. If s[i] == 'a', you should replace it with 'z'.
This operation costs previousCost[j] where j is the index of s[i] in the alphabet.

The shift distance is the minimum total cost of operations required to transform s into t.

Return the shift distance from s to t.


Example 1:

Input: s = "abab", t = "baba", nextCost = [100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
previousCost = [1,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

Output: 2

Explanation:

We choose index i = 0 and shift s[0] 25 times to the previous character for a total cost of 1.
We choose index i = 1 and shift s[1] 25 times to the next character for a total cost of 0.
We choose index i = 2 and shift s[2] 25 times to the previous character for a total cost of 1.
We choose index i = 3 and shift s[3] 25 times to the next character for a total cost of 0.
Example 2:

Input: s = "leet", t = "code", nextCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
previousCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]

Output: 31

Explanation:

We choose index i = 0 and shift s[0] 9 times to the previous character for a total cost of 9.
We choose index i = 1 and shift s[1] 10 times to the next character for a total cost of 10.
We choose index i = 2 and shift s[2] 1 time to the previous character for a total cost of 1.
We choose index i = 3 and shift s[3] 11 times to the next character for a total cost of 11.


Constraints:

1 <= s.length == t.length <= 10^5
s and t consist only of lowercase English letters.
nextCost.length == previousCost.length == 26
0 <= nextCost[i], previousCost[i] <= 10^9
 */
public class ShiftDistanceBetweenTwoStrings {
    // my solution with pref sums
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        // next: abcdefg abcdefg
        long[] nextSum = new long[27];
        long[] prevSum = new long[27];
        for(int i = 0; i < 26; i++) {
            nextSum[i+1] = nextSum[i] + nextCost[i];
            prevSum[i+1] = prevSum[i] + previousCost[25-i];
        }
        long ans = 0;
        for(int i = 0; i < s.length(); i++) {
            int sInd = s.charAt(i) - 'a';
            int tInd = t.charAt(i) - 'a';
            if (sInd == tInd) continue;
            long minShiftSum;
            if (sInd < tInd) {
                minShiftSum = Math.min(getSum(sInd, tInd-1, nextSum),
                    getSum(25-sInd, 25, prevSum) + getSum(0, 25-tInd-1, prevSum));
            } else {
                // sInd > tInd
                minShiftSum = Math.min(getSum(25-sInd, 25-tInd-1, prevSum),
                    getSum(sInd, 25, nextSum) + getSum(0, tInd-1, nextSum));
            }
            ans += minShiftSum;
        }
        return ans;
    }

    // if (to == -1) then returns 0 because in that case from = 0
    private long getSum(int from, int to, long[] pref) {
        return pref[to+1] - pref[from];
    }

    // public solution - very similar to my solution but slightly different implementation
    /*
    Approach

        Calculate the prefix and suffix cost.

        Now for 0 <= i < n : if s[i] != t[i] we have two cases:

        Case 1: s[i] < t[i]
        (s[i] = b, t[i] = f)
        Forward shifting = cost from b to e // --->
        Backward shifting = cost from b to a + cost from z to g // <---

        Case 2: s[i] > t[i]
        (s[i] = f, t[i] = b)
        Forward shifting = cost from f to z + a to a // --->
        Backward shifting = cost from f to c // <----
     */
    public long shiftDistancePublicPrefSums(String s, String t, int[] nextCost, int[] previousCost) {
        int n = s.length();
        long[] next = new long[26];
        long[] prev = new long[26];

        for (int i = 0; i < 26; i++) {
            next[i] = nextCost[i];
            prev[i] = previousCost[i];
        }

        // Calculate cumulative costs for next and previous
        for (int i = 1; i < 26; i++) {
            next[i] += next[i - 1];
        }
        for (int i = 24; i >= 0; i--) {
            prev[i] += prev[i + 1];
        }

        long cost = 0;
        for(int i = 0; i < n; i++){
            if(s.charAt(i) != t.charAt(i)){
                int start = s.charAt(i) - 'a', end = t.charAt(i) - 'a';

                if(start < end){ // case 1
                    long forwardCost = next[end - 1] - (start > 0 ? next[start - 1] : 0);
                    long backwardCost = prev[0] - (start + 1 < 26 ? prev[start + 1] : 0)
                        + (end == 25 ? 0 : prev[end +1]);
                    cost += Math.min(forwardCost, backwardCost);
                }
                else{ // case 2
                    long backwardCost = prev[end + 1] - (start >= 25 ? 0 : prev[start + 1]);
                    long forwardCost = next[25] - next[start - 1] + (end != 0 ? next[end - 1] : 0);
                    cost += Math.min(forwardCost, backwardCost);
                }
            }
        }

        return cost;
    }
}
