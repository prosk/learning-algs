package com.mycompany.yandex;

import java.util.Scanner;

public class LongestOneSeq {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int cnt = s.nextInt();

        int[] arr = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            arr[i] = s.nextInt();
        }

        int maxOneLen = 0, currCnt = 0;
        boolean begin = false;
        for (int i = 0; i < cnt; i++) {
            if (arr[i] == 1) {
                if (begin) {
                    currCnt++;
                } else {
                    begin = true;
                    currCnt++;
                }
            } else {
                if (begin) {
                    begin = false;
                    if (currCnt > maxOneLen) {
                        maxOneLen = currCnt;
                    }
                    currCnt = 0;
                }
            }
        }
        if (currCnt > maxOneLen) {
            maxOneLen = currCnt;
        }
        System.out.println(maxOneLen);
    }
}
