package com.mycompany.leetcode.medium;

// https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
public class Task1493 {
    public static void main(String[] args) {
        Task1493 task = new Task1493();
        int[] test1 = {1,1,0,1}, test2 = {0,1,1,1,0,1,1,0,1}, test3 = {1,1,1};


        int ans1 = task.longestSubarray(test1);
        int ans2 = task.longestSubarray(test2);
        int ans3 = task.longestSubarray(test3);

        System.out.println(ans1 + " " + ans2 + " " + ans3);
    }

    public int longestSubarray(int[] nums) {
        int prevOneWindow = 0, currOneWindow = 0;
        int prevLastInd = -1, currFirstInd = -1;
        int maxLen = 0, currLen = 0;
        for(int i = 0; i <= nums.length; i++) {
            int currElem = (i == nums.length) ? 0 : nums[i];
            if (currElem == 1) {
                currOneWindow++;
                currFirstInd = (currFirstInd == -1) ? i : currFirstInd;
            } else {
                if (currOneWindow > 0) {
                    currLen = (prevLastInd != -1 && (currFirstInd - prevLastInd) == 2) ?
                            prevOneWindow + currOneWindow : currOneWindow;
                    maxLen = Math.max(maxLen, currLen);
                    prevOneWindow = currOneWindow;
                    currOneWindow = 0;
                    currFirstInd = -1;
                    prevLastInd = i - 1;
                }
            }
        }
        return (maxLen == nums.length) ? maxLen - 1 : maxLen ;
    }
}
