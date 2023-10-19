package com.mycompany.frombooks.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InversionsCounter {
    public static void main(String[] args) {
        List<Integer> test1 = Arrays.asList(5, 3, 2, 18, 34, 7);
        System.out.println("Number of inversions in " + Arrays.toString(test1.toArray()) + " is " +
                countInversions(test1));
        System.out.println("Array after counting: " + Arrays.toString(test1.toArray()));

        List<Integer> test2 = Arrays.asList(5, 20, 30, 180, 340, 700);
        System.out.println("Number of inversions in " + Arrays.toString(test2.toArray()) + " is " +
                countInversions(test2));
        System.out.println("Array after counting: " + Arrays.toString(test2.toArray()));

        List<Integer> test3 = Arrays.asList(30, 22, 15, 7, 3);
        System.out.println("Number of inversions in " + Arrays.toString(test3.toArray()) + " is " +
                countInversions(test3));
        System.out.println("Array after counting: " + Arrays.toString(test3.toArray()));
    }

    public static int countInversions(List<Integer> A) {
        return countSubarrayInversions(A, 0, A.size());
    }

    // Return the number of inversions in A.subList(start, end).
    private static int countSubarrayInversions(List<Integer> A, int start, int end) {
        if (end - start <= 1) {
            return 0;
        }

        int mid = start + ((end - start) / 2);
        return countSubarrayInversions(A, start, mid)
                + countSubarrayInversions(A, mid, end)
                + mergeSortAndCountInversionsAcrossSubarrays(A, start, mid, end);
    }

    // Merge two sorted sublists A.subList(start, mid) and A.subList(mid, end)
    // into A.subList(start, end) and return the number of inversions across
    // A.subList(start, mid) and A.subList(mid, end).
    private static int mergeSortAndCountInversionsAcrossSubarrays(List<Integer> A,
                                                                  int start, int mid, int end) {
        List<Integer> sortedA = new ArrayList<>();
        int leftStart = start, rightStart = mid, inversionCount = 0;
        while (leftStart < mid && rightStart < end) {
            if (Integer.compare(A.get(leftStart), A.get(rightStart)) <= 0) {
                sortedA.add(A.get(leftStart++));
            } else {
                // A.subList(leftStart, mid) are the inversions of A[rightStart].
                inversionCount += mid - leftStart;
                sortedA.add(A.get(rightStart++));
            }
        }
        sortedA.addAll(A.subList(leftStart, mid));
        sortedA.addAll(A.subList(rightStart, end));
        // Updates A with sortedA.
        for (Integer t : sortedA) {
            A.set(start++, t);
        }
        return inversionCount;
    }
}
