package com.mycompany.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/4sum/description/?envType=company&envId=yandex&favoriteSlug=yandex-three-months
// 18
// 4Sum
public class FourSumInArray {

    public static void main(String[] args) {
        int[] test1 = {1,0,-1,0,-2,2};
        FourSumInArray fourSumInArray = new FourSumInArray();
        List<List<Integer>> ans = fourSumInArray.fourSum(test1, 0);
        for(List<Integer> list: ans) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int firstInd = 0; firstInd <= nums.length - 4; firstInd++) {
            if (firstInd > 0 && nums[firstInd - 1] == nums[firstInd])
                continue;
            for(int secondInd = firstInd + 1; secondInd <= nums.length - 3; secondInd++) {
                if (secondInd > firstInd + 1 && nums[secondInd - 1] == nums[secondInd])
                    continue;
                findTwo(nums, firstInd, secondInd, target, ans);
            }
        }
        return ans;
    }

    private void findTwo(int[] nums, int firstInd, int secondInd, int target, List<List<Integer>> ans) {
        long sum = (long)nums[firstInd] + nums[secondInd];
        long rest = target - sum;
        int start = secondInd + 1, end = nums.length - 1;
        while(start < end) {
            long currSum = (long)nums[start] + nums[end];
            if (currSum < rest) {
                start++;
            } else if (currSum > rest) {
                end--;
            } else {
                List<Integer> quad = Arrays.asList(nums[firstInd], nums[secondInd], nums[start], nums[end]);
                ans.add(quad);
                start++;
                end--;

                while (start < end && nums[start - 1] == nums[start]) start++;

                while (start < end && nums[end + 1] == nums[end]) end--;
            }
        }
    }

    // two pointer public solution
    // https://leetcode.com/problems/4sum/solutions/5966278/easiest-beats-java-two-pointers/?envType=company&envId=yandex&favoriteSlug=yandex-three-months
    public List<List<Integer>> fourSumWithTwoPointers(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i])
                continue;

            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j])
                    continue;

                int k = j + 1;
                int l = len - 1;

                while (k < l) {
                    long sum = nums[i] + nums[j];
                    sum += nums[k] + nums[l];

                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;

                        while (k < l && nums[k - 1] == nums[k]) k++;

                        while (k < l && nums[l + 1] == nums[l]) l--;
                    } else if (sum < target)
                        k++;
                    else
                        l--;
                }
            }
        }

        return ans;
    }


}
