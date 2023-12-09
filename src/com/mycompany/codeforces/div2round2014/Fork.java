package com.mycompany.codeforces.div2round2014;

import com.mycompany.codeforces.div3round913.BrokenKeyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Fork {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Fork().run();
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
        int t = readInt();

        for(int i = 0; i < t; i++) {
            int a = readInt();
            int b = readInt();
            int xK = readInt();
            int yK = readInt();
            int xQ = readInt();
            int yQ = readInt();

            boolean abEquals = (a == b);
            List<Integer> rowStep = new ArrayList<>();
            List<Integer> colStep = new ArrayList<>();

            if (abEquals) {
                rowStep.addAll(Arrays.asList(a, -a));
                colStep.addAll(Arrays.asList(a, -a));
            } else {
                rowStep.addAll(Arrays.asList(a, -a, b, -b));
                colStep.addAll(Arrays.asList(a, -a, b, -b));
            }

            Set<Pair> beatK = new HashSet<>();
            for(int r = 0; r < rowStep.size(); r++) {
                for(int c = 0; c < colStep.size(); c++) {
                    if (Math.abs(rowStep.get(r)) != Math.abs(colStep.get(c)) || abEquals) {
                        beatK.add(new Pair(xK + rowStep.get(r), yK + colStep.get(c)));
                    }
                }
            }

            int ans = 0;
            for(int r = 0; r < rowStep.size(); r++) {
                for(int c = 0; c < colStep.size(); c++) {
                    if (Math.abs(rowStep.get(r)) != Math.abs(colStep.get(c)) || abEquals) {
                        Pair qPair = new Pair(xQ + rowStep.get(r), yQ + colStep.get(c));
                        if (beatK.contains(qPair))
                            ans++;
                    }
                }
            }

            out.println(ans);
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

    private static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
