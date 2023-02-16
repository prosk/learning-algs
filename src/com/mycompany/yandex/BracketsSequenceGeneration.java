package com.mycompany.yandex;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BracketsSequenceGeneration {
    private static int n;
    private static byte[] currSeq;
    private static byte[] nextLine = "\n".getBytes(StandardCharsets.UTF_8);
    private static byte[] LEFT_BRACKET = "(".getBytes(StandardCharsets.UTF_8);
    private static byte[] RIGHT_BRACKET = ")".getBytes(StandardCharsets.UTF_8);
    private static int leftBracketLength = LEFT_BRACKET.length;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        currSeq = new byte[2*n];
        generateAllRightIntSeq(0, 0, 0);
    }

    /*public static void generateAllRightSeq(String currSeq, int currLeft, int currRight) {
        if (currSeq.length() == 2*n) {
            System.out.println(currSeq);
        } else {
            if (currLeft == currRight) {
                generateAllRightSeq(currSeq + "(", currLeft+1, currRight);
            } else if (currLeft > currRight) {
                if (currLeft < n) {
                    generateAllRightSeq(currSeq + "(", currLeft + 1, currRight);
                    generateAllRightSeq(currSeq + ")", currLeft, currRight+1);
                } else {
                    generateAllRightSeq(currSeq + ")", currLeft, currRight+1);
                }
            }
        }
    }*/

    public static void generateAllRightIntSeq(int currLeft, int currRight, int currLength) {
        if (currLength == 2*n) {
            for (int i = 0; i < 2*n; i++) {
                System.out.write(currSeq[i] == 1 ? 40 : 41);
            }
            System.out.write(10);
        } else {
            if (currLeft == currRight) {
                currSeq[currLength] = 1;
                generateAllRightIntSeq(currLeft+1, currRight, currLength+1);
            } else if (currLeft > currRight) {
                if (currLeft < n) {
                    currSeq[currLength] = 1;
                    generateAllRightIntSeq(currLeft + 1, currRight, currLength+1);
                    currSeq[currLength] = 2;
                    generateAllRightIntSeq(currLeft, currRight+1, currLength+1);
                } else {
                    for(int j = currLength; j < 2*n; j++) {
                        currSeq[j] = 2;
                    }
                    generateAllRightIntSeq(currLeft, n, 2*n);
                }
            }
        }
    }

}