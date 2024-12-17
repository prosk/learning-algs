package com.mycompany.leetcode.bytopic.twopointers;

// 16
// 3Sum Closest

/*
Given an integer array nums of length n and an integer target, find three integers in nums
such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Example 2:

Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 */

import java.util.Arrays;

public class TripleSumClosest {

    // my solution with two pointers
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length-2; i++) {
            ans = findClosest(nums, i, target, ans);
            if (ans == target) break;
        }
        return ans;
    }

    private int findClosest(int[] nums, int i, int target, int closest) {
        int l = i + 1, r = nums.length - 1;
        int newClosest = closest;
        while(l < r) {
            int sum = nums[i] + nums[l] + nums[r];
            if (Math.abs(target - sum) < Math.abs(target - newClosest)) {
                newClosest = sum;
            }
            if (sum > target) {
                r--;
            } else if (sum < target) {
                l++;
            } else {
                return target;
            }
        }
        return newClosest;
    }
}
