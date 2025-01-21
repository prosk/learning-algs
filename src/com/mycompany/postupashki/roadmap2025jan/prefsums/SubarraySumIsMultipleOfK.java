package com.mycompany.postupashki.roadmap2025jan.prefsums;

import java.util.HashMap;

public class SubarraySumIsMultipleOfK {

    public static void main(String[] args) {
        SubarraySumIsMultipleOfK runner = new SubarraySumIsMultipleOfK();
        int[] test1 = {23,2,4,6,7};
        boolean ans1 = runner.checkSubarraySum(test1, 6);
        System.out.println(ans1);

        int[] test2 = {1, 0};
        boolean ans2 = runner.checkSubarraySum(test2, 2);
        System.out.println(ans2);
    }

    // ed solution
    public boolean checkSubarraySum(int[] nums, int k) {
        int prefixMod = 0;
        HashMap<Integer, Integer> modSeen = new HashMap<>();
        modSeen.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefixMod = (prefixMod + nums[i]) % k;

            if (modSeen.containsKey(prefixMod)) {
                // ensures that the size of subarray is at least 2
                if (i - modSeen.get(prefixMod) > 1) {
                    return true;
                }
            } else {
                // mark the value of prefixMod with the current index.
                modSeen.put(prefixMod, i);
            }
        }
        return false;
    }
}
