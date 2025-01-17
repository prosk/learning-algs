package com.mycompany.postupashki.roadmap2025jan.prefsums;

//https://leetcode.com/problems/find-the-pivot-integer/editorial/?envType=problem-list-v2&envId=prefix-sum
// 2485. Find the Pivot Integer
/*
    Given a positive integer n, find the pivot integer x such that:

The sum of all elements between 1 and x inclusively equals the sum of all elements
between x and n inclusively.
Return the pivot integer x. If no such integer exists, return -1.
It is guaranteed that there will be at most one pivot index for the given input.



Example 1:

Input: n = 8
Output: 6
Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 is the pivot integer since: 1 = 1.
Example 3:

Input: n = 4
Output: -1
Explanation: It can be proved that no such integer exist.


Constraints:

1 <= n <= 1000
 */
public class PivotInteger {

    // my solution O(n)
    public int pivotInteger(int n) {
        int allSum = n * (n+1) / 2;
        int leftSum = 0, rightSum;
        int ans = -1;
        for(int i = 1; i <= n; i++) {
            leftSum += i;
            rightSum = allSum - leftSum + i;
            if (leftSum == rightSum) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    // editorial solution with two pointers
    public int pivotIntegerEdTwoPointers(int n) {
        int leftValue = 1;
        int rightValue = n;
        int sumLeft = leftValue;
        int sumRight = rightValue;

        if (n == 1) return n;

        // Iterate until the pointers meet
        while (leftValue < rightValue) {
            // Adjust sums and pointers based on comparison
            if (sumLeft < sumRight) {
                sumLeft += ++leftValue;
            } else {
                sumRight += --rightValue;
            }

            // Check for pivot condition
            if (sumLeft == sumRight && leftValue + 1 == rightValue - 1) {
                return leftValue + 1;
            }
        }

        return -1; // Return -1 if no pivot is found
    }


    // editorial solution with binary search O(logN)
    public int pivotIntegerEdBinSearch(int n) {
        // Initialize left and right pointers for binary search
        int left = 1, right = n;

        // Calculate the total sum of the sequence
        int totalSum = n * (n + 1) / 2;

        // Binary search for the pivot point
        while (left < right) {
            // Calculate the mid-point
            int mid = (left + right) / 2;

            // Check if the difference between the square of mid and the total sum is negative
            if (mid * mid - totalSum < 0) {
                left = mid + 1; // Adjust the left bound if the sum is smaller
            } else {
                right = mid; // Adjust the right bound if the sum is equal or greater
            }
        }

        // Check if the square of the left pointer minus the total sum is zero
        if (left * left - totalSum == 0) {
            return left;
        } else {
            return -1;
        }
    }

    // editorial math solution
    public int pivotIntegerEdMath(int n) {
        final int sum = (n * (n + 1) / 2);
        final int pivot = (int) Math.sqrt(sum);
        // If pivot * pivot is equal to sum (pivot found) return pivot, else return -1
        return pivot * pivot == sum ? pivot : -1;
    }

    // strange editorial precompute solution
    private static final int maxValue = 1000;
    // Array to store precomputed pivot values
    private static int[] precompute = new int[maxValue + 1];

    public int pivotIntegerEdPrecompute(int n) {
        // Initializing to 0
        for (int i = 0; i <= maxValue; i++) {
            precompute[i] = 0;
        }

        // Check if precompute array is not initialized
        if (precompute[1] == 0) {
            for (int i = 1, j = 1; i <= maxValue; ++i) {
                int sum = i * (i + 1) / 2;

                // Find the first square greater than or equal to sum
                while (j * j < sum) {
                    ++j;
                }

                // Check if j * j is equal to sum (pivot found), otherwise set to -1
                precompute[i] = j * j == sum ? j : -1;
            }
        }

        return precompute[n];
    }

}
