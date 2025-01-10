package com.mycompany.leetcode.bytopic.twopointers;

// 1493
// Longest Subarray of 1's After Deleting One Element

/*
    Given a binary array nums, you should delete one element from it.

    Return the size of the longest non-empty subarray containing only 1's in the
    resulting array. Return 0 if there is no such subarray.

    Example 1:

    Input: nums = [1,1,0,1]
    Output: 3
    Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
    Example 2:

    Input: nums = [0,1,1,1,0,1,1,0,1]
    Output: 5
    Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
    Example 3:

    Input: nums = [1,1,1]
    Output: 2
    Explanation: You must delete one element.

 */

public class LongestSubarrayOfOnes {

    // my solution with two pointers
    public int longestSubarray(int[] nums) {
        int ans = 0, zeroCnt = 1 - nums[0];
        for(int left = 0, right = 0; left < nums.length; left++) {
            while(zeroCnt <= 1 && right < nums.length-1) {
                right++;
                zeroCnt += 1 - nums[right];
            }
            ans = Math.max(ans, right - left - zeroCnt + 1);
            if (nums[left] == 0) zeroCnt--;
        }
        return ans == nums.length ? ans-1 : ans;
    }

    // editorial solution
    // в моем решении слева окно каждый раз уменьшается на 1, а справа сдвигается вперед пока все ОК
    // в решении ниже наоборот - справа на каждой итерации добавлятся ровно 1 элемент, а слева
    // сдвигается пока не станет ОК
    public int longestSubarrayEd(int[] nums) {
        // Number of zero's in the window.
        int zeroCount = 0;
        int longestWindow = 0;
        // Left end of the window.
        int start = 0;

        for (int i = 0; i < nums.length; i++) {
            zeroCount += (nums[i] == 0 ? 1 : 0);

            // Shrink the window until the count of zero's
            // is less than or equal to 1.
            while (zeroCount > 1) {
                zeroCount -= (nums[start] == 0 ? 1 : 0);
                start++;
            }

            // all window size = i - start + 1, and one symbol we have to delete
            longestWindow = Math.max(longestWindow, i - start);
        }

        return longestWindow;
    }

    // решение на основе ДП без 2-х указателей
    /*
        Это, фактически, динамическое программирование. Но можно о нем рассуждать и не зная ДП.
        F(i,k) — максимальная длина из '1' в конце списка из первых i элементов, если оттуда удалить k элементов.
        Для k=0, оно считается в currentOnes.
        При k=1, оно считается в currentResult и там выбираются 2 варианта
        — или элемент удален раньше (currentResult + 1) или удаляется последний элемент (currentOnes).
     */

    public int longestSubarrayDpBtfl(int[] nums) {
        // Max sequence of 1s at the end of processed list.
        int currentOnes = 0;
        // Max sequence of 1s at the end of processed list,
        // if exactly one element is removed.
        int currentResult = -1;
        int result = 0;
        for(int e: nums) {
            if (e == 1) {
                currentResult = Math.max(currentResult + 1, currentOnes);
                currentOnes++;
            } else {
                currentResult = currentOnes;
                currentOnes = 0;
            }
            if (currentResult > result)
                result = currentResult;
        }
        return result;
    }

}
