package com.mycompany.postupashki.roadmap2025jan.prefsums;

/*
Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
Example 2:

Input: arr = [1,2]
Output: 3
Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
Example 3:

Input: arr = [10,11,12]
Output: 66


Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 1000


Follow up:

Could you solve this problem in O(n) time complexity?
 */

//1588. Sum of All Odd Length Subarrays
// https://leetcode.com/problems/sum-of-all-odd-length-subarrays/description/?envType=problem-list-v2&envId=prefix-sum
public class SumOfOddLengthSubarrays {

    // my solution with DP
    public int sumOddLengthSubarrays(int[] arr) {
        //  1,  4, -2,  2,  5,  7,  3, 8
        //  0   1   2   3   4   5   6, 7
        // dpEven[i] - сумма отрезков заканчивающихся в индексе i четной длины
        // dpOdd[i] - сумма отрезков заканчивающихся в индексе i НЕчетной длины
        int[] dpEven = new int[arr.length];
        int[] dpOdd = new int[arr.length];
        dpEven[0] = 0;
        dpOdd[0] = arr[0];
        int sum = dpOdd[0];
        for(int i = 1; i < arr.length; i++) {
            int currLen = i+1;
            if (currLen % 2 == 0) {
                dpOdd[i] = dpEven[i-1] + i/2 * arr[i] + arr[i];
                dpEven[i] = dpOdd[i-1] + (i/2 + 1) * arr[i];
            } else {
                dpOdd[i] = dpEven[i-1] + i/2 * arr[i] + arr[i];
                dpEven[i] = dpOdd[i-1] + (i/2) * arr[i];
            }
            sum += dpOdd[i];
        }
        return sum;
    }

    // O(n*n) ed solution
    public int sumOddLengthSubarraysEd(int[] arr) {
        int n = arr.length, answer = 0;

        for (int left = 0; left < n; ++left) {
            int currentSum = 0;
            for (int right = left; right < n; ++right) {
                currentSum += arr[right];
                answer += (right - left + 1) % 2 == 1 ? currentSum : 0;
            }
        }
        return answer;
    }

    // smart ed solution
    /*
    Intuition

        Instead of finding all odd-length subarrays, we can count the number of occurrences
        of each integer in all odd-length subarrays. For example, if arr[i]
        has appeared k times, it contributes to the total sum by arr[i] * k.

        Let's find the pattern behind this: since the current subarray containing arr[i] has an odd-length,
        the number of elements without arr[i] must be even, indicating the number of elements
        to the left and right side of arr[i] must be both even or both odd

        even left and even right
               X
           L L X R R
       L L L L X R R R R

       odd left and odd right
             L X R
         L L L X R R R
     L L L L L X R R R R R

     if we have a segment of length = LEN (to the left or to the right side) then
     count of subsegments with Even count of elems = (LEN/2 + 1)
     count of subsegments with Odd count of elems = (LEN + 1)/2

     */

    public int sumOddLengthSubarraysEdBtfl(int[] arr) {
        int n = arr.length, answer = 0;

        for (int i = 0; i < n; ++i) {
            int left = i, right = n - i - 1;
            answer += arr[i] * (left / 2 + 1) * (right / 2 + 1); // even elems left AND even elems right
            answer += arr[i] * ((left + 1) / 2) * ((right + 1) / 2); // odd elems left AND odd elems right
        }

        return answer;
    }

}
