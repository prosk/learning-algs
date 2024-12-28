package com.mycompany.codeforces.goodbye2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class BestImpressionistOpt {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BestImpressionistOpt().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    void solve() {
        int n = readInt();
        int[][] ranges = new int[n][2];
        Map<Integer, Integer> points = new HashMap<>();
        List<Integer> pointsList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int l = readInt();
            int r = readInt();
            ranges[i][0] = l;
            ranges[i][1] = r;
            if (l == r) {
                int cnt = points.merge(l, 1, Integer::sum);
                if (cnt == 1) {
                    pointsList.add(l);
                }
            }
        }
        Collections.sort(pointsList);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            boolean uniq = false;
            int l = ranges[i][0];
            int r = ranges[i][1];
            if (l == r) {
                int cnt = points.get(l);
                if (cnt == 1) uniq = true;
            } else {
                // l < r
                int minPointInd = leftBound(pointsList, l);
                int maxPointInd = rightBound(pointsList, r);
                if (minPointInd != -1 && maxPointInd != -1 &&
                    pointsList.get(minPointInd).equals(l) &&
                    pointsList.get(maxPointInd).equals(r)) {
                    int rangeCnt = r - l + 1;
                    int pointsCnt = maxPointInd - minPointInd + 1;
                    uniq = rangeCnt > pointsCnt;
                } else {
                    uniq = true;
                }
            }
            sb.append(uniq ? '1' : '0');
        }
        out.println(sb);
    }

    // минимальный индекс i, для которого a[i] >= left
    // если все a[i] < left, возвращает -1
    int leftBound(List<Integer> a, int left) {
        int res = -1;
        int l = 0, r = a.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a.get(mid) >= left) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    // максимальный индекс i, для которого a[i] <= right
    // если все a[i] больше right, вернет -1
    int rightBound(List<Integer> a, int right) {
        int res = -1;
        int l = 0, r = a.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a.get(mid) <= right) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
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