package com.mycompany.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Task1964 {

    public static void main(String[] args) {
        //int[] input = new int[] {3, 1, 5, 6, 1, 4, 2, 2, 2, 1};
        //int[] input = new int[] {3, 1, 5, 6, 4, 2};
        //int[] input = new int[] {1, 2, 3, 2};
        int[] input = new int[] {2, 2, 1};
        int[] res = new Task1964().longestObstacleCourseAtEachPosition(input);
        System.out.println(Arrays.toString(res));
    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int len = obstacles.length;
        int[] nearestLeft = getNearestLeft(obstacles);
        int res[] = new int[len];
        res[0] = 1;
        for(int i = 1; i < len; i++) {
            int nearestLeftIndex = nearestLeft[i];
            if (nearestLeftIndex == 0) {
                res[i] = 1;
            } else {
                res[i] = res[nearestLeftIndex-1] + 1;
            }
        }
        return res;
    }

    public int[] getNearestLeft(int[] obstacles) {
        int len = obstacles.length;
        Deque<Integer> myStack = new ArrayDeque<>();
        int[] nearestLeft = new int[len];

        int i = 1;
        myStack.push(len-1);
        while (i < len) {
            int currElem = obstacles[len-i-1];
            int onTopElemIndex = myStack.peek();

            if (currElem <= obstacles[onTopElemIndex]) {
                int currOnTopElemIndex;
                while(!myStack.isEmpty()) {
                    currOnTopElemIndex = myStack.peek();
                    if (currElem <= obstacles[currOnTopElemIndex]) {
                        nearestLeft[currOnTopElemIndex] = len-i;
                        myStack.pop();
                    } else {
                        break;
                    }
                }
            }
            myStack.push(len-i-1);
            i++;
        }
        return nearestLeft;
    }
}
