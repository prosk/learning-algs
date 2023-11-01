package com.mycompany.yandex.training40.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class Elevator {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Elevator().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        int k = readInt();
        int n = readInt();

        int[] arr = new int[n+1];
        List<Integer> targetFloors = new ArrayList<>();
        targetFloors.add(0);
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
            if (arr[i] > 0) {
                targetFloors.add(i);
            }
        }

        if (targetFloors.size() == 1) {
            out.println(0);
            return;
        }

        BigInteger bigAns = BigInteger.valueOf(targetFloors.get(targetFloors.size()-1));
        long ans = targetFloors.get(targetFloors.size()-1), rest = 0;
        for(int i = targetFloors.size()-1; i >= 1; i--) {
            // инвариант - на входе в итерацию цикла мы уже находимся на этаже targetFloors.get(i)
            // и в ans уже добавлено время попадания на этот этаж
            int currFloor = targetFloors.get(i);
            long currCnt = (long)arr[currFloor] + rest;
            if (currCnt >= k) {
                long currGroupCnt = currCnt / k;
                long newRest = currCnt % k;
                long timeToPrevFloor;
                if (newRest == 0) {
                    // если остатка нет мы перевозим currGroupCnt групп вниз
                    // и потом поднимаемся снизу на targetFloors.get(i-1)
                    timeToPrevFloor = 2 * currFloor * currGroupCnt - currFloor + targetFloors.get(i-1);
                    rest = 0;
                } else {
                    // если остаток есть мы перевозим currGroupCnt групп вниз и потом поднимаемся за остатком
                    // опять на currFloor; остаток перевозим на targetFloors.get(i-1)
                    timeToPrevFloor = 2 * currFloor * currGroupCnt + (currFloor - targetFloors.get(i-1));
                    rest = newRest;
                }
                ans += timeToPrevFloor;
                bigAns = bigAns.add(BigInteger.valueOf(timeToPrevFloor));
            } else {
                // если еще не набрали k человек просто едем вниз до targetFloors.get(i-1)
                rest = currCnt;
                ans += (currFloor - targetFloors.get(i-1));
                bigAns = bigAns.add(BigInteger.valueOf(currFloor - targetFloors.get(i-1)));
            }
        }

        // out.println(ans);
        out.println(bigAns);
    }

    private int readInt() {
        return Integer.parseInt(readString());
    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

