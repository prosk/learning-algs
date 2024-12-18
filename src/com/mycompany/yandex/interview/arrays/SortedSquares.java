package com.mycompany.yandex.interview.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 Дан массив целых чисел длиной N, массив упорядочен по возрастанию
 Написать функцию, которая из этого массива получит массив квадратов чисел,
 отсортированный по возрастанию
 Итоговая асимптотика равна O(log(N) + N) = O(N)
 */

// leetcode 977
// https://leetcode.com/problems/squares-of-a-sorted-array/
public class SortedSquares {

    public static void main(String[] args) {
        SortedSquares sq = new SortedSquares();
        int[] test1 = {-3, 2, 4};
        int[] ans = sq.getSortedSquaresOpt(test1);
        System.out.println(Arrays.toString(ans));

        int[] test2 = {-5, -3, -1, 0, 0, 2, 4};
        int[] ans2 = sq.getSortedSquaresOpt(test2);
        System.out.println(Arrays.toString(ans2));

        int[] test3 = {-5};
        int[] ans3 = sq.getSortedSquaresOpt(test3);
        System.out.println(Arrays.toString(ans3));

        int[] test4 = {50};
        int[] ans4 = sq.getSortedSquaresOpt(test4);
        System.out.println(Arrays.toString(ans4));

        int[] test5 = {};
        int[] ans5 = sq.getSortedSquaresOpt(test5);
        System.out.println(Arrays.toString(ans5));

        int[] test6 = {-5, -4, -2};
        int[] ans6 = sq.getSortedSquaresOpt(test6);
        System.out.println(Arrays.toString(ans6));

        int[] test7 = {5, 10, 20};
        int[] ans7 = sq.getSortedSquaresOpt(test7);
        System.out.println(Arrays.toString(ans7));

        int[] test8 = {-10, -10, -10, 0, 0, 0, 5, 5, 5};
        int[] ans8 = sq.getSortedSquaresOpt(test8);
        System.out.println(Arrays.toString(ans8));
    }

    // простое решение - идем от от больших квадратов к меньшим
    // если в массиве только отрицательные или только положительные числа это тоже будет работать
    public int[] getSortedSquaresOpt(int[] arr) {
        int i = 0, j = arr.length - 1;
        int[] res = new int[arr.length];
        for(int k = arr.length - 1; k >= 0; k--) {
            if (Math.abs(arr[i]) > Math.abs(arr[j])) {
                res[k] = arr[i]*arr[i];
                i++;
            } else {
                res[k] = arr[j]*arr[j];
                j--;
            }
        }
        return res;
    }

    // первое решение которое пришло в голову - найти границу между <0 и >=0, и далее от этой границы двигаться влево и вправо
    // в принципе решение ОК, но если подумать что можно идти от больших квадратов к меньшим - тогда решение получается
    // намного проще
    public int[] getSortedSquares(int[] arr) {
        int[] squares = new int[arr.length];
        int midIndex = getMidIndex(arr);
        int right = (midIndex == -1) ? arr.length : midIndex;
        int left = right - 1, square = 0;
        for(int i = 0; i < arr.length; i++) {
            int rightVal = (right == arr.length) ? Integer.MAX_VALUE : arr[right];
            int leftVal = (left < 0) ? Integer.MAX_VALUE : Math.abs(arr[left]);
            if (rightVal <= leftVal) {
                square = rightVal*rightVal;
                right++;
            } else {
                square = leftVal*leftVal;
                left--;
            }
            squares[i] = square;
        }
        return squares;
    }

    // min i such than arr[i] >= 0
    private int getMidIndex(int[] arr) {
        int ans = -1;
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int mid = l + (r - l)/2;
            if (arr[mid] >= 0) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}