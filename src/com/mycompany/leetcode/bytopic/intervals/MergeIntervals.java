package com.mycompany.leetcode.bytopic.intervals;

/*

    Given an array of intervals where intervals[i] = [starti, endi],
    merge all overlapping intervals, and return an array of the non-overlapping intervals
    that cover all the intervals in the input.

    Example 1:

    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

    Example 2:

    Input: intervals = [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// https://leetcode.com/problems/merge-intervals/description/?envType=company&envId=yandex&favoriteSlug=yandex-thirty-days
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] test1 = {{1,3}, {2,6},{8,10},{15,18} };
        int[][] ans = mergeBtfl(test1);
        System.out.println("Test1");
        for(int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }

        int[][] test2 = {{1,4}, {4,5} };
        ans = mergeBtfl(test2);
        System.out.println("Test2");
        for(int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }

        // Merge with scan line
        System.out.println("Merge with scan line");
        int[][] test11 = {{1,3}, {2,6},{8,10},{15,18} };
        ans = mergeWithScanLine(test11);
        System.out.println("Test1");
        for(int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }

        int[][] test22 = {{1,4}, {4,5} };
        ans = mergeWithScanLine(test22);
        System.out.println("Test2");
        for(int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
    }

    public static int[][] mergeBtfl(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            int[] lastElem = merged.size() == 0 ? null : merged.get(merged.size()-1);
            if (merged.isEmpty() || lastElem[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                lastElem[1] = Math.max(lastElem[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    // my original solution (complicated)

    public static int[][] mergeWithScanLine(int[][] intervals) {
        int pointsCount = intervals.length * 2;

        Point[] points = new Point[pointsCount];

        int j = 0;
        for(int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] != intervals[i][1]) {
                points[j++] = new Point(intervals[i][0], 2);
                points[j++] = new Point(intervals[i][1], 1);
            } else {
                // особый случай, точка-интервал, где начало и конец в одной координате
                points[j++] = new Point(intervals[i][0], 3);
                points[j++] = new Point(intervals[i][0], 3);
            }
        }

        Arrays.sort(points, null);

        List<ResInterval> resList = new ArrayList<>();

        int currSum = 0, i = 0;
        boolean startActive = false;
        int currStartCoord = -1, lastEndCoord = -1;
        while(i < points.length) {
            Point currPoint = points[i];
            if (currPoint.openCloseType == 2) {
                currSum++;
                if (!startActive) {
                    startActive = true;
                    currStartCoord = currPoint.coord;
                }
            } else if (currPoint.openCloseType == 1) {
                currSum--;
                if (currSum == 0 && startActive) {
                    // кандидат на закрытие результирующего интервала
                    boolean existStartPointInSameCoord = (i+1) < points.length &&
                        points[i+1].coord == currPoint.coord &&
                        points[i+1].openCloseType == 2;

                    if (!existStartPointInSameCoord) {
                        // закрываем интервал
                        startActive = false;

                        ResInterval r = new ResInterval(currStartCoord, currPoint.coord);
                        resList.add(r);

                        lastEndCoord = currPoint.coord;
                    }

                }

            } else {
                // интервал-точка
                if (!startActive && currSum == 0 && currPoint.coord != lastEndCoord) {
                    // если он отделен от всего другого, возвращаем его как отдельный
                    ResInterval r = new ResInterval(currPoint.coord, currPoint.coord);
                    resList.add(r);
                    lastEndCoord = currPoint.coord;
                }

            }
            i++;
        }

        int[][] res = new int[resList.size()][2];

        int resIndex = 0;
        for(ResInterval resInt: resList) {
            res[resIndex][0] = resInt.startCoord;
            res[resIndex][1] = resInt.endCoord;
            resIndex++;
        }

        return res;
    }

    private static class Point implements Comparable<Point> {
        int coord;
        int openCloseType;

        public Point(int coord, int openCloseType) {
            this.coord = coord;
            this.openCloseType = openCloseType;
        }

        @Override
        public int compareTo(Point anotherPoint) {
            int res = Integer.compare(this.coord, anotherPoint.coord);
            return res == 0 ? Integer.compare(this.openCloseType, anotherPoint.openCloseType) : res;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return coord == point.coord && openCloseType == point.openCloseType;
        }

        @Override
        public int hashCode() {
            return Objects.hash(coord, openCloseType);
        }

    }

    private static class ResInterval {
        int startCoord;
        int endCoord;

        public ResInterval(int startCoord, int endCoord) {
            this.startCoord = startCoord;
            this.endCoord = endCoord;
        }

    }

}
