package com.mycompany.postupashki.yandex;

import java.util.HashMap;
import java.util.Map;

public class LcTaskSubarraySumEqualsK560 {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int nums[] = new int[] {0, 0, 0, 0, 0 , 0};
        int test1 = slt.subarraySum(nums, 0);
        System.out.println(test1);
    }

    private static class Solution {
        public int subarraySum(int[] nums, int k) {
            // calc pref sums  O(n)
            int[] pref = new int[nums.length + 1];
            pref[0] = 0;
            for(int i = 0; i < nums.length; i++) {
                pref[i+1] = pref[i] + nums[i];
            }
            // pref[i] = sum(nums[0]...nums[i-1])
            // iterate over pref sums O(n)
            // sum[i, j] = pref[j+1] - pref[i]
            int cnt = 0;
            Map<Integer, Integer> seen = new HashMap<>();
            for(int j = 0; j < pref.length; j++) {
                int prevCnt = seen.getOrDefault(pref[j] - k, 0);
                cnt += prevCnt;
                seen.merge(pref[j], 1, Integer::sum);
            }
            return cnt;
        }
    }

    class BtflSolution {
        public int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> dict = new HashMap<>();
            int ans = 0, sum = 0;
            dict.put(0, 1);
            for(int i = 0; i < nums.length; i++) {
                sum += nums[i];
                int diff = sum - k;
                ans += dict.getOrDefault(diff, 0);
                dict.merge(sum, 1, Integer::sum);
            }
            return ans;
        }
    }
}
