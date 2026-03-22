package com.mycompany.postupashki.yandex;

import java.util.Arrays;

public class LcTaskMoveZeroes283 {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[] nums = new int[] {0,1,0,3,12};
        slt.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static class Solution {
        public void moveZeroes(int[] nums) {
            int prevZeroes = 0;
            for(int i = 0; i < nums.length; i++) {
                boolean isZero = nums[i] == 0;
                if (prevZeroes > 0 && !isZero) {
                    nums[i - prevZeroes] = nums[i];
                    nums[i] = 0;
                }
                prevZeroes += isZero ? 1 : 0;
            }
        }
    }

    private static class SolutionWithTwoFor {
        public void moveZeroes(int[] nums) {
            int writePos = 0;
            for(int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[writePos++] = nums[i];
                }
            }
            for(int j = writePos; j < nums.length; j++) {
                nums[j] = 0;
            }
        }
    }

    private static class SolutionWithTwoIf {
        public void moveZeroes(int[] nums) {
            int writePos = 0;
            for(int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[writePos] = nums[i];
                    if (writePos != i) {
                        nums[i] = 0;
                    }
                    writePos++;
                }
            }
        }
    }
}
