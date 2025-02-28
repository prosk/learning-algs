package com.mycompany.postupashki.roadmap2025jan.prefsums;

import java.util.HashSet;
import java.util.Set;

// 1546 Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
/*
Given an array nums and an integer target, return the maximum number of non-empty
non-overlapping subarrays such that
the sum of values in each subarray is equal to target.
 */
public class MaxCntOfSubarraysWithTargetSum {
    // my solution with pref sum and greedy algorithm
    // O(n)
    public int maxNonOverlapping(int[] nums, int target) {
        // 3 4 5 6 10
        int ans = 0, pref = 0;
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        for(int num: nums) {
            pref += num;
            if(seen.contains(pref - target)) {
                ans++;
                pref = 0;
                seen = new HashSet<>();
                seen.add(0);
            } else {
                seen.add(pref);
            }
        }
        return ans;
    }
}
