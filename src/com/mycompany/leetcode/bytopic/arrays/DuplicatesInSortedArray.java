package com.mycompany.leetcode.bytopic.arrays;

import java.util.Arrays;

/*

 */

// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?source=submission-ac
public class DuplicatesInSortedArray {

    public static void main(String[] args) {
        int[] test1 = new int[]{0,0,1,1,1,1,2,3,3,4,4,4,4,4, 8, 9};

        int ans1 = removeDuplicates(test1);
        System.out.println(ans1);
        System.out.println(Arrays.toString(test1));
    }


    public static int removeDuplicatesBtfl(int[] nums) {
        if (nums.length <= 2) return nums.length;

        int j = 2;
        for(int i = 2; i < nums.length; i++) {
            nums[j] = nums[i];
            if (nums[j] != nums[j - 2]) j++;
        }
        return j;
    }


    // 3 4 4 4 4 4 7 7 7 7 7 8 8 8 8 8 8 8 9 9 9 10 11 12 13 14
    public static int removeDuplicates(int[] nums) {
        int currFreePos = Integer.MAX_VALUE;
        int i = 1, curr = nums[0], cnt = 1;
        while(i < nums.length) {
            // update cnt and curr
            if (nums[i] == curr) {
                cnt++;
            } else {
                curr = nums[i];
                cnt = 1;
            }
            // update with currFreePos
            if (cnt == 3 && currFreePos == Integer.MAX_VALUE) {
                currFreePos = i;
            } else if (cnt < 3 && currFreePos <= i) {
                nums[currFreePos] = nums[i];
                currFreePos++;
            }
            i++;
        }
        return (currFreePos == Integer.MAX_VALUE) ? i : currFreePos;
    }
}
