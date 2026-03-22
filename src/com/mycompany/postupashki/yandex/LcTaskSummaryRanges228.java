package com.mycompany.postupashki.yandex;

import java.util.ArrayList;
import java.util.List;

public class LcTaskSummaryRanges228 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {0,1,2,4,5,7};
        List<String> ans = solution.summaryRanges(nums);
        System.out.println(ans);
    }

    private static class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> ranges = new ArrayList<>();
            for(int left = 0, right = 0; left < nums.length; left = right+1) {
                right = left;
                // right == left; [left, right] - диапазон
                while(right+1 < nums.length && nums[right+1] == nums[right]+1) {
                    right++;
                }
                // right >= left, [left, right] - диапазон, который нельзя расширить дальше
                ranges.add(getRangeDesc(nums[left], nums[right]));
            }
            return ranges;
        }

        private String getRangeDesc(int startInd, int endInd) {
            return startInd == endInd ? String.valueOf(startInd)
                    : String.format("%d->%d", startInd, endInd);
        }
    }
}
