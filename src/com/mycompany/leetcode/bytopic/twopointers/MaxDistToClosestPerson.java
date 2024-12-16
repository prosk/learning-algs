package com.mycompany.leetcode.bytopic.twopointers;

// 849
// https://leetcode.com/problems/maximize-distance-to-closest-person/description/?envType=company&envId=yandex&favoriteSlug=yandex-three-months

/*
You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

Return that maximum distance to the closest person.
 */

public class MaxDistToClosestPerson {

    // my solution
    public int maxDistToClosest(int[] seats) {
        // 0001
        // 00100
        // 0100001
        int lastSitting = -1, ans = 0;
        for(int i = 0; i < seats.length; i++) {
            if (seats[i] == 1 && lastSitting == -1) {
                ans = Math.max(ans, i);
                lastSitting = i;
            } else if (seats[i] == 1) {
                int mid = (lastSitting + i)/2;
                ans = Math.max(ans, mid - lastSitting);
                lastSitting = i;
            }
        }
        ans = Math.max(ans, seats.length - 1 - lastSitting);
        return ans;
    }
}
