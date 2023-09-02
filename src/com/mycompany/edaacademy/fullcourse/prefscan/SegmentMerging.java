package com.mycompany.edaacademy.fullcourse.prefscan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SegmentMerging {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SegmentMerging().solve();
        out.close();
    }

    private void solve() {
        // data input
        int segCnt = readInt();
        int[][] segments = new int[segCnt][2];

        for(int i = 0; i < segCnt; i++) {
            segments[i][0] = readInt();
            segments[i][1] = readInt();
        }

        Arrays.sort(segments, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : segments) {
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

        out.println(merged.size());
        for(int[] interval : merged) {
            out.println(interval[0] + " " + interval[1]);
        }
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
