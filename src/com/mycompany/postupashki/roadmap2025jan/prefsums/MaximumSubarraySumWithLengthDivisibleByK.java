package com.mycompany.postupashki.roadmap2025jan.prefsums;

import java.util.HashMap;
import java.util.Map;

public class MaximumSubarraySumWithLengthDivisibleByK {

    // pref sum solution O(n)
    public long maxSubarraySum(int[] nums, int k) {
        // index % k --> min prefix sum
        // k = 3
        // (0) 1 2 (2) 3 (0) 4 5 6 (0) 7 8 (2) 9 (0)
        Map<Integer, Long> minSums = new HashMap<>();
        minSums.put(0, 0L);
        long prefSum = 0, ans = Long.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            prefSum += nums[i];
            int r = (i+1) % k;
            if (minSums.containsKey(r)) {
                long minSum = minSums.get(r);
                long newSegmentSum = prefSum - minSum;
                ans = Math.max(ans, newSegmentSum);
                if (prefSum < minSum) {
                    minSums.put(r, prefSum);
                }
            } else {
                minSums.put(r, prefSum);
            }
        }
        return ans;
    }
}
