package com.mycompany.yandex.training30.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class ExamWork {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new ExamWork().run();
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
        int studentsCnt = readInt();
        int variantsCnt = readInt();
        int rowNumForPetya = readInt();
        int orderNumInRowForPetya = readInt();

        int petyaSeatNum = getSeatNum(rowNumForPetya, orderNumInRowForPetya);
        int petyaPartNum = getPartNum(petyaSeatNum);
        int beforeSeatNum = petyaSeatNum - variantsCnt;
        int afterSeatNum = petyaSeatNum + variantsCnt;

        int beforePartDiff = (beforeSeatNum > 0) ? petyaPartNum - getPartNum(beforeSeatNum) : -1;
        int afterPartDiff = (afterSeatNum <= studentsCnt) ? getPartNum(afterSeatNum) - petyaPartNum : -1;

        if (beforePartDiff == -1 && afterPartDiff == -1) {
            out.println("-1");
            return;
        }

        int resultSeatNum;
        if (beforePartDiff != -1 && afterPartDiff != -1) {
            resultSeatNum = beforePartDiff < afterPartDiff ? beforeSeatNum : afterSeatNum;
        } else {
            resultSeatNum = (beforePartDiff == -1) ? afterSeatNum : beforeSeatNum;
        }
        printResult(resultSeatNum);
    }

    private void printResult(int seatNum) {
        int partNum = getPartNum(seatNum);
        int r = seatNum % 2;
        int orderInPartNum = (r > 0) ? 1 : 2;
        out.println(partNum + " " + orderInPartNum);
    }

    private int getPartNum(int seatNum) {
        int partNum = seatNum / 2;
        int r = seatNum % 2;
        if (r > 0) {
            partNum++;
        }
        return partNum;
    }

    private int getSeatNum(int rowNum, int orderNumInRow) {
        return (rowNum-1)*2 + orderNumInRow;
    }

    private int getVarNum(int seatNum, int variantsCnt) {
        int r = seatNum % variantsCnt;
        return r == 0 ? variantsCnt : r;
    }

    private int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = readInt();
        return a;
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
