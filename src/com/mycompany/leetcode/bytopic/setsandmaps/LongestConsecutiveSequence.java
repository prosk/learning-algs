package com.mycompany.leetcode.bytopic.setsandmaps;

// 128
// https://leetcode.com/problems/longest-consecutive-sequence/editorial/

/*
    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

    You must write an algorithm that runs in O(n) time.

    Example 1:

    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

    Example 2:

    Input: nums = [0,3,7,2,5,8,4,6,0,1]
    Output: 9
*/

import java.util.*;

public class LongestConsecutiveSequence {

    // editorial solution with sort
    public int longestConsecutiveEdWithSort(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentStreak += 1;
                } else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }

    // editorial solution with set

    /*
    Time complexity : O(n).

    Although the time complexity appears to be quadratic due to the while
    loop nested within the for loop, closer inspection reveals it to be
    linear. Because the while loop is reached only when currentNum marks
    the beginning of a sequence (i.e. currentNum-1 is not present in
    nums), the while loop can only run for n iterations throughout the
    entire runtime of the algorithm. This means that despite looking like
    O(n⋅n) complexity, the nested loops actually run in O(n+n)=O(n)
    time. All other computations occur in constant time, so the overall
    runtime is linear.
     */

    public int longestConsecutiveEdWithSet(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    // interesting union find solution
    // https://leetcode.com/problems/longest-consecutive-sequence/solutions/166544/union-find-thinking-process/
    public int longestConsecutiveWithUnionFind(int[] nums) {
        UF uf = new UF(nums.length);
        // Map val to index in nums
        Map<Integer, Integer> valToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (valToIndex.containsKey(nums[i])) {
                continue;
            }

            if (valToIndex.containsKey(nums[i] - 1)) {
                uf.union(i, valToIndex.get(nums[i] - 1));
            }

            if (valToIndex.containsKey(nums[i] + 1)) {
                uf.union(i, valToIndex.get(nums[i] + 1));
            }

            valToIndex.put(nums[i], i);
        }

        return uf.getLargetComponentSize();
    }

    class UF {
        private int[] parent;
        private int[] size;

        public UF(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) { // connected if consecutive
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        };

        private int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        };

        public int getLargetComponentSize() {
            int maxSize = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == i && size[i] > maxSize) {
                    maxSize = size[i];
                }
            }

            return maxSize;
        }
    }
}
