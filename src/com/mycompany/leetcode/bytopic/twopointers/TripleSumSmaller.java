package com.mycompany.leetcode.bytopic.twopointers;

// 259
// 3Sum Smaller

/*
Given an array of n integers nums and an integer target,
find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition
nums[i] + nums[j] + nums[k] < target.

Example 1:

Input: nums = [-2,0,1,3], target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
[-2,0,1]
[-2,0,3]
Example 2:

Input: nums = [], target = 0
Output: 0
Example 3:

Input: nums = [0], target = 0
Output: 0
 */

import java.util.Arrays;

public class TripleSumSmaller {

    // my solution with two pointers
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        for(int i = 0; i < nums.length-2; i++) {
            ans += findTwoCnt(nums, i, target);
        }
        return ans;
    }

    private int findTwoCnt(int[] nums, int i, int target) {
        int l = i + 1, r = nums.length - 1, cnt = 0;
        while(l < r) {
            int sum = nums[i] + nums[l] + nums[r];
            if (sum >= target) {
                r--;
            } else {
                cnt += r - l;
                l++;
            }
        }
        return cnt;
    }
}
