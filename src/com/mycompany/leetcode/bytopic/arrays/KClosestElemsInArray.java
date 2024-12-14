package com.mycompany.leetcode.bytopic.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KClosestElemsInArray {

    /*
    Given a sorted integer array arr, two integers k and x,
    return the k closest integers to x in the array.
    The result should also be sorted in ascending order.

       An integer a is closer to x than an integer b if:
        |a - x| < |b - x|, or
        |a - x| == |b - x| and a < b

        Example 1:

        Input: arr = [1,2,3,4,5], k = 4, x = 3

        Output: [1,2,3,4]

     */

    public static void main(String[] args) {
        int[] test1 = new int[]{1,2,3,4,5};

        List<Integer> ans1 = findClosestElements(test1, 4, 3);
        System.out.println(ans1);
    }

    // -------------------------------------------------------------------------
    // my solution with binary search + sliding window
    // -------------------------------------------------------------------------

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int firstInd = getFirstInd(arr, x);
        if (firstInd == -1) {
            // arr[i] < x for all i
            for(int i = arr.length - k; i < arr.length; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
        if (firstInd > 0) {
            int prevInd = firstInd-1;
            if (Math.abs(x - arr[prevInd]) <= Math.abs(x - arr[firstInd])) {
                firstInd = prevInd;
            }
        }
        int l = firstInd, r = firstInd, cnt = 1;
        while(cnt < k) {
            // l and r indexes is in k-size window
            int right = (r < arr.length-1) ? arr[r+1] : Integer.MAX_VALUE;
            int left = (l > 0) ? arr[l-1] : Integer.MIN_VALUE;
            long rightDiff = (long)right - x;
            long leftDiff = (long)x - left;
            if (rightDiff == leftDiff || rightDiff > leftDiff) {
                l--;
            } else {
                r++;
            }
            cnt++;
        }
        for(int j = l; j <= r; j++) {
            ans.add(arr[j]);
        }
        return ans;
    }

    // return first elem >= k
    private static int getFirstInd(int[] arr, int val) {
        int ans = -1;
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int mid = l + (r - l)/2;
            if (arr[mid] >= val) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // -------------------------------------------------------------------------
    // editorial solution with binary search + sliding window
    // -------------------------------------------------------------------------

    public static List<Integer> findClosestElementsEd(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();

        // Base case
        if (arr.length == k) {
            for (int i = 0; i < k; i++) {
                result.add(arr[i]);
            }

            return result;
        }

        // Binary search to find the closest element
        int left = 0;
        int right = arr.length;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Initialize our sliding window's bounds
        left -= 1;
        right = left + 1;

        // While the window size is less than k
        while (right - left - 1 < k) {
            // Be careful to not go out of bounds
            if (left == -1) {
                right += 1;
                continue;
            }

            // Expand the window towards the side with the closer number
            // Be careful to not go out of bounds with the pointers
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left -= 1;
            } else {
                right += 1;
            }
        }

        // Build and return the window
        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    // -------------------------------------------------------------------------
    // editorial solution with sorting
    // -------------------------------------------------------------------------

    public static List<Integer> findClosestElementsSort(int[] arr, int k, int x) {
        // Convert from array to list first to make use of Collections.sort()
        List<Integer> sortedArr = new ArrayList<>();
        for (int num: arr) {
            sortedArr.add(num);
        }

        // Sort using custom comparator
        Collections.sort(sortedArr,
            (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));

        // Only take k elements
        sortedArr = sortedArr.subList(0, k);

        // Sort again to have output in ascending order
        Collections.sort(sortedArr);
        return sortedArr;
    }

    // -------------------------------------------------------------------------
    // editorial clever solution with custom binary search and best complexity
    // -------------------------------------------------------------------------

    public List<Integer> findClosestElementsBtfl(int[] arr, int k, int x) {
        // Initialize binary search bounds
        int left = 0;
        int right = arr.length - k;

        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Create output in correct format
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }



}
