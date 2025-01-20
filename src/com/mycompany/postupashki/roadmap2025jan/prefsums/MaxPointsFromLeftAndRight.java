package com.mycompany.postupashki.roadmap2025jan.prefsums;


/*
There are several cards arranged in a row, and each card has an associated number of points.
The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row.
You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.



Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.
Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.


Constraints:

1 <= cardPoints.length <= 10^5
1 <= cardPoints[i] <= 10^4
1 <= k <= cardPoints.length
 */
// 1423 Maximum Points You Can Obtain from Cards
// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/editorial/
public class MaxPointsFromLeftAndRight {

    // my solution with pref sums and sliding window O(n)
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int wLen = len - k;
        int[] pref = new int[len+1];
        for(int i = 0; i < len; i++) {
            pref[i+1] = pref[i] + cardPoints[i];
        }
        // j = begin index of the window
        int minSum = Integer.MAX_VALUE;
        for(int j = 0; j <= len - wLen; j++) {
            int currSum = pref[j+wLen] - pref[j];
            if (currSum < minSum) {
                minSum = currSum;
            }
        }
        int ans = pref[len] - minSum;
        return ans;
    }

    // ed solution with pref sums O(n)
    public int maxScoreEdSlidingWindow(int[] cardPoints, int k) {
        int startingIndex = 0;
        int presentSubarrayScore = 0;
        int n = cardPoints.length;
        int requiredSubarrayLength = n - k;
        int minSubarrayScore;
        int totalScore = 0;

        // Total score obtained on selecting all the cards.
        for (int i : cardPoints) {
            totalScore += i;
        }

        minSubarrayScore = totalScore;

        if (k == n) {
            return totalScore;
        }

        for (int i = 0; i < n; i++) {
            presentSubarrayScore += cardPoints[i];
            int presentSubarrayLength = i - startingIndex + 1;
            // If a valid subarray (having size cardsPoints.length - k) is possible.
            if (presentSubarrayLength == requiredSubarrayLength) {
                minSubarrayScore = Math.min(minSubarrayScore, presentSubarrayScore);
                presentSubarrayScore -= cardPoints[startingIndex++];
            }
        }
        return totalScore - minSubarrayScore;
    }

    // ed solution with DP O(k)
    public int maxScoreEdDp(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int[] frontSetOfCards = new int[k + 1];
        int[] rearSetOfCards = new int[k + 1];

        for (int i = 0; i < k; i++) {
            frontSetOfCards[i + 1] = cardPoints[i] + frontSetOfCards[i];
            rearSetOfCards[i + 1] = cardPoints[n - i - 1] + rearSetOfCards[i];
        }

        int maxScore = 0;
        // Each i represents the number of cards we take from the front.
        for (int i = 0; i <= k; i++) {
            int currentScore = frontSetOfCards[i] + rearSetOfCards[k - i];
            maxScore = Math.max(maxScore, currentScore);
        }

        return maxScore;
    }

    // space optimized ed solution with DP O(k)
    public int maxScoreEdDpBtfl(int[] cardPoints, int k) {
        int frontScore = 0;
        int rearScore = 0;
        int n = cardPoints.length;

        for (int i = 0; i < k; i++) {
            frontScore += cardPoints[i];
        }

        // take all k cards from the beginning
        int maxScore = frontScore;

        // take i from the beginning and k - i from the end
        for (int i = k - 1; i >= 0; i--) {
            rearScore += cardPoints[n - (k - i)];
            frontScore -= cardPoints[i];
            int currentScore = rearScore + frontScore;
            maxScore = Math.max(maxScore, currentScore);
        }

        return maxScore;
    }

}
