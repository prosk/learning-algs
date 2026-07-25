package com.mycompany.leetcode.bytopic.arrays;

import java.util.Arrays;

public class RotateImage {

    public static void main(String[] args) {
        // test
        RotateImage rotateImage = new RotateImage();
        int[][] test4 = {
            {5,1,9,11},
            {2,4,8,10},
            {13,3,6,7},
            {15,14,12,16}};
        rotateImage.rotateInitial(test4);
        print(test4);
    }

    public static void print(int[][] matrix) {
        String s = "{ ";
        for(int i = 0; i < matrix.length; i++) {
            s += Arrays.toString(matrix[i]) + "\n";
        }
        System.out.println(s);
    }

    // my initial solution
    public void rotateInitial(int[][] matrix) {
        int n = matrix.length;
        int iterations = n / 2;
        int rowPos = 0, colPos = 0, side = n;
        for(int i = 0; i < iterations; i++) {
            // do rotate for one perimeter
            for(int r = 0; r < side - 1; r++) {
                int leftUpCol = colPos + r; // row = rowPos
                int rightUpRow = rowPos + r; // col = colPos + side - 1
                int bottomRightCol = colPos + side - 1 - r; // row = rowPos + side - 1
                int bottomLeftRow = rowPos + side - 1 - r; // col = colPos
                // replace 4 elems
                int tmp = matrix[rowPos][leftUpCol];
                matrix[rowPos][leftUpCol] = matrix[bottomLeftRow][colPos];
                matrix[bottomLeftRow][colPos] = matrix[rowPos + side - 1][bottomRightCol];
                matrix[rowPos + side - 1][bottomRightCol] = matrix[rightUpRow][colPos + side - 1];
                matrix[rightUpRow][colPos + side - 1] = tmp;
            }
            // adjust to new perimeter
            rowPos++;
            colPos++;
            side -= 2;
        }
    }

    // my simplified solution
    public void rotateSimple(int[][] matrix) {
        int n = matrix.length;
        int iterations = n / 2;
        int begOffset = 0, side = n;
        for(int i = 0; i < iterations; i++) {
            // do rotate for one perimeter
            for(int r = 0; r < side - 1; r++) {
                int endOffset = begOffset + side - 1;
                // 4 угла квадрата это [begOffset][begOffset] - левый верхний
                // [endOffset][begOffset] - левый нижний; [endOffset][endOffset] - правый нижний
                // [begOffset][endOffset] - правый верхний
                // replace 4 elems
                // [begOffset][begOffset + r] - левый верхний бежит вправо
                // [endOffset - r][begOffset] - левый нижний бежит вверх
                // [endOffset][endOffset - r] - правый нижний бежит влево
                // [begOffset + r][endOffset] - правый верхний бежит вниз
                int tmp = matrix[begOffset][begOffset + r];
                matrix[begOffset][begOffset + r] = matrix[endOffset - r][begOffset];
                matrix[endOffset - r][begOffset] = matrix[endOffset][endOffset - r];
                matrix[endOffset][endOffset - r] = matrix[begOffset + r][endOffset];
                matrix[begOffset + r][endOffset] = tmp;
            }
            // adjust to new perimeter
            begOffset++;
            side -= 2;
        }
    }

    // just shorter version of rotateSimple code without comments
    // для меня наиболее четкая версия для понимания
    public void rotate(int[][] matrix) {
        int iterations = matrix.length / 2;
        int begIdx = 0, side = matrix.length;
        for(int i = 0; i < iterations; i++) {
            for(int r = 0; r < side - 1; r++) {
                int endIdx = begIdx + side - 1;
                int tmp = matrix[begIdx][begIdx + r];
                matrix[begIdx][begIdx + r] = matrix[endIdx - r][begIdx];
                matrix[endIdx - r][begIdx] = matrix[endIdx][endIdx - r];
                matrix[endIdx][endIdx - r] = matrix[begIdx + r][endIdx];
                matrix[begIdx + r][endIdx] = tmp;
            }
            begIdx++;
            side -= 2;
        }
    }

    // version equal to previous but some diferent variables
    // from Cracking the Coding Interview
    public void rotateOneMore(int[][] matrix) {
        for(int layer = 0; layer < matrix.length / 2; layer++) {
            int first = layer;
            int last = matrix.length - 1 - layer;
            for(int i = first; i < last; i++) {
                int offset = i - first;
                int tmp = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = tmp;
            }
        }
    }

}
