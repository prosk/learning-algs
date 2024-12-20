package com.mycompany.leetcode.bytopic.arrays;

// 3105
// https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/description/?envType=company&envId=yandex&favoriteSlug=yandex-three-months
public class MonotonicSubarray {

    // my solution
    public int longestMonotonicSubarray(int[] nums) {
        int incMaxLen = 1, decMaxLen = 1;
        int pos = 0;
        while(pos < nums.length) {
            int incPos = pos;
            while(incPos+1 < nums.length && nums[incPos+1] > nums[incPos]) {
                incPos++;
            }
            int decPos = pos;
            while(decPos+1 < nums.length && nums[decPos+1] < nums[decPos]) {
                decPos++;
            }
            incMaxLen = Math.max(incMaxLen, incPos-pos+1);
            decMaxLen = Math.max(decMaxLen, decPos-pos+1);
            int nextPos = Math.max(incPos, decPos);
            pos = Math.max(nextPos, pos+1);
        }
        return Math.max(incMaxLen, decMaxLen);
    }

    // beatiful one loop solution
    public int longestMonotonicSubarrayBtfl(int[] nums) {
        int ans = 1, inc = 1, dec = 1;
        for(int i = 1; i < nums.length; i++) {
            inc = (nums[i-1] < nums[i]) ? inc+1 : 1;
            dec = (nums[i-1] > nums[i]) ? dec+1 : 1;
            ans = Math.max(ans, Math.max(inc, dec));
        }
        return ans;
    }

    // good solution with simple logic
    public int longestMonotonicSubarrayGood(int[] nums) {
        int maxLength = 1;
        int incLength = 1;
        int decLength = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                incLength++;
                decLength = 1;
            } else if(nums[i] < nums[i - 1]) {
                decLength++;
                incLength = 1;
            } else {
                incLength = 1;
                decLength = 1;
            }
            maxLength = Math.max(maxLength, Math.max(incLength, decLength));
        }
        return maxLength;
    }
}
