package com.mycompany.leetcode.bytopic.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// разбор на neetcode: https://neetcode.io/solutions/find-k-closest-elements
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
        int[] test2 = new int[]{3, 7, 12, 15, 16, 16, 45, 77, 89, 90, 92, 95, 96, 96, 98};

        List<Integer> ans1 = findClosestElementsBtfl(test2, 3, 10);
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

    /*

    Let's consider two indices at each binary search operation, the usual mid, and some index mid + k.
    The relationship between these indices is significant because only one of them could possibly
    be in a final answer. For example, if mid = 2, and k = 3, then arr[2] and arr[5] could not possibly
    both be in the answer, since that would require taking 4 elements [arr[2], arr[3], arr[4], arr[5]].

    This leads us to the question: how do we move our pointers left and right?
    If the element at arr[mid] is closer to x than arr[mid + k], then that means arr[mid + k],
    as well as every element to the right of it can never be in the answer.
    This means we should move our right pointer to avoid considering them. The logic is the same vice-versa -
    if arr[mid + k] is closer to x, then move the left pointer.

     */

    public static List<Integer> findClosestElementsBtfl(int[] arr, int k, int x) {
        // Initialize binary search bounds
        int left = 0;
        int right = arr.length - k;

        // l и r - это левая и правая границы для индекса НАЧАЛА окна размера k (индекс первого элемента в окне)

        // invariant left < right (l < r] m = (l + r) / 2
        // case1    [l][][m][][][r]
        // после присваиваний l = m + 1 или r = m инвариант l < r сохраняется:
        // l = m + 1: [l][][r]   r = m: [l][][r]
        // case2    [l][][m][][r]
        // после присваиваний l = m + 1 или r = m инвариант l < r сохраняется:
        // l = m + 1: [l][r]   r = m: [l][][r]
        // case3    [l][m][][r]
        // после присваиваний l = m + 1 или r = m инвариант l < r сохраняется:
        // l = m + 1: [l][r]   r = m: [l][r]
        // case4    [l][m][r]
        // после присваиваний l = m + 1 становится l = r и инвариант больше НЕ! выполняется
        // после присванивания r = m становится [l][r] и инвариант верен
        // case5 [l=m][r]
        // после присваиваний l = m + 1 становится l = r и инвариант больше НЕ! выполняется
        // после присванивания r = m становится l = r и инвариант больше НЕ! выполняется

        // Итого есть 3 случая выхода из цикла
        // 1) [l][m][r] и после l = m + 1 получается что l перепрыгивает вперед (правее) до r
        // 2) [l][r] и после l = m + 1 также l перепрыгивает вперед (правее) до r
        // 3) [l][r] и после r = m наоборот r прыгает левее до l

        // l может прыгать в r, и наборотот, поэтому границы изначальные строго допустимы
        // сначала l = 0 и r = len - k - в обоих индексах окно может начинаться
        // если начало окна в индексе (len - k), то это наиболее "правое" окно из k элементов
        // далее в цикле l может только увеличиваться либо остаться таким же, но не уменьшаться!!!
        // аналогично, далее r в цикле может уменьшится либо остаться таким же, но не увеличится!!!
        // после выхода из цикла всегда будет l = r, и невозможно, чтобы r ушло левее r,
        // то есть невозможно, чтобы стало l > r

        // 3  7  12  15  16  16  45  77  89  90  92  95  96  96  98
        // len = 15
        // k = 3 - достаточно мелкое окно
        // предположим мы задали x = 10 тогда ответом является окно [7, 12, 15]

        // [3]  7  12  15  16  16  {45}  77  89  {90}  92  95  [96]  96  98
        // сравниваем 45 и 90, какой из них ближе к 10? конечно 45
        // итого l = 0, r = mid = 6
        // [3]  7  12  {15}  16  16  {[45]}  77  89  90  92  95  96  96  98
        // сравниваем 15 и 45, ближе к десяти находится 15
        // итого l = 0, r = mid = 3
        // [3]  {7}  12  [15]  {16}  16  45  77  89  90  92  95  96  96  98
        // здесь видно что mid+k может вылезти правее r - это нормально
        // сравниваем 7 и 16, из них к 10 ближе 7
        // итого l = 0, r = mid = 1
        // {[3]}  [7]  12  {15}  16  16  45  77  89  90  92  95  96  96  98
        // опять mid+k сильно вылезает правее r
        // сравниваем 3 и 15, и тут впервые к 10 ближе то значение, которое правее (15)
        // поэтому впервые l = m + 1 = 1 и становится l = r = 1 (l прыгнуло правее до r)
        // выходим из цикла - начало окна в индексе 1

        // Binary search against the criteria described
        int iter = 1;
        while (left < right) {
            int mid = (left + right) / 2;
            /*System.out.println(String.format("Iteration %d before: l = %d, r = %d, m = %d", iter,
                    left, right, mid));*/
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1; // mid и все что левее можно выбросить => минимальное левое окно [mid+1; mid+1+k-1]
            } else {
                right = mid; // mid+k и все что правее можно выбросить => максимальное правое окно это [mid; mid+k-1]
            }
            /*System.out.println(String.format("Iteration %d after: l = %d, r = %d", iter,
                    left, right));
            iter++;*/
        }

        // Create output in correct format
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }



}
