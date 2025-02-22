package com.mycompany.leetcode.bytopic.dp;

// 221. Maximal Square
// https://leetcode.com/problems/maximal-square/description/
/*
Given an m x n binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.
 */
// task from Google interview
public class MaxSquareOf1sInBinaryMatrix {

    public static void main(String[] args) {
        MaxSquareOf1sInBinaryMatrix runner = new MaxSquareOf1sInBinaryMatrix();
        char[][] test = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        int ans = runner.maximalSquare(test);
        System.out.println(ans);
    }

    // dp solution
    // Time complexity :O(m*n). Single pass.
    // Space complexity :O(m*n). Another matrix of same size is used for dp.
    public int maximalSquare(char[][] matrix) {
        // dp[i][j] = maximum square side length with bottom right corner
        // in the cell [i][j]
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        int ans = 0;
        for(int i = 1; i < matrix.length+1; i++) {
            for(int j = 1; j < matrix[0].length+1; j++) {
                if (matrix[i-1][j-1] == '1') {
                    int up = dp[i-1][j];
                    int left = dp[i][j-1];
                    int diag = dp[i-1][j-1];
                    dp[i][j] = Math.min(up, Math.min(left, diag)) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans*ans;
    }
}
