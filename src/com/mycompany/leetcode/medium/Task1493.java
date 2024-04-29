package com.mycompany.leetcode.medium;

// https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
public class Task1493 {
    public static void main(String[] args) {
        Task1493 task = new Task1493();
        int[] test1 = {1,1,0,1}, test2 = {0,1,1,1,0,1,1,0,1}, test3 = {1,1,1};


        int ans1 = task.longestSubarray2(test1);
        int ans2 = task.longestSubarray2(test2);
        int ans3 = task.longestSubarray2(test3);

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
        return (maxLen == nums.length) ? maxLen - 1 : maxLen;
    }

    // int[] test1 = {1,1,0,1}, test2 = {0,1,1,1,0,1,1,0,1}, test3 = {1,1,1};
    public int longestSubarray2(int[] nums) {
        // Maintain a sliding window where there is at most one zero on it.
        int left = 0, zeroCnt = 0;
        int currLen = 0, maxLen = 0, lastZeroIndex = -1;
        for(int right = 0; right < nums.length; right++) {
            zeroCnt += (1 - nums[right]);

            if (zeroCnt <= 1) {
                currLen = right - left + 1 - zeroCnt;
                maxLen = Math.max(maxLen, currLen);
            } else {
                left = lastZeroIndex + 1;
                zeroCnt--;
            }

            if (nums[right] == 0)
                lastZeroIndex = right;
        }
        return (maxLen == nums.length) ? maxLen - 1 : maxLen;
    }
}
