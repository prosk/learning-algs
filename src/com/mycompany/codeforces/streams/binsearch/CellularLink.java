package com.mycompany.codeforces.streams.binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


// https://codeforces.com/group/yeVhAfeK6s/contest/571840/problem/C
public class CellularLink {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new CellularLink().run();
        out.close();
    }

    void run() {
        int cityCnt = readInt();
        int towerCnt = readInt();
        int[] citiesCoords = new int[cityCnt];
        int[] towersCoords = new int[towerCnt];
        for(int i = 0; i < cityCnt; i++) citiesCoords[i] = readInt();
        for(int i = 0; i < towerCnt; i++) towersCoords[i] = readInt();
        // solve
        int maxMinDist = 0;
        for(int i = 0; i < cityCnt; i++) {
            int leftMinDist = getLeftNearestTowerDist(citiesCoords[i], towersCoords);
            int rightMinDist = getRightNearestTowerDist(citiesCoords[i], towersCoords);
            int minDist = Math.min(leftMinDist, rightMinDist);
            maxMinDist = Math.max(maxMinDist, minDist);
        }
        out.println(maxMinDist);
    }

    int getLeftNearestTowerDist(int cityCoord, int[] towersCoords) {
        int l = 0, r = towersCoords.length-1, ans = -1;
        boolean leftFounded = false;
        while(l <= r) {
            int mid = l + (r - l)/2;
            if (towersCoords[mid] <= cityCoord) {
                ans = towersCoords[mid];
                l = mid + 1;
                leftFounded = true;
            } else {
                r = mid - 1;
            }
        }
        return leftFounded ? (cityCoord - ans) : Integer.MAX_VALUE;
    }

    int getRightNearestTowerDist(int cityCoord, int[] towersCoords) {
        int l = 0, r = towersCoords.length-1, ans = -1;
        boolean rightFounded = false;
        while(l <= r) {
            int mid = l + (r - l)/2;
            if (towersCoords[mid] >= cityCoord) {
                ans = towersCoords[mid];
                r = mid - 1;
                rightFounded = true;
            } else {
                l = mid + 1;
            }
        }
        return rightFounded ? (ans - cityCoord) : Integer.MAX_VALUE;
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}