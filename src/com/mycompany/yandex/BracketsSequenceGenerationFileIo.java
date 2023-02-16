package com.mycompany.yandex;

import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class BracketsSequenceGenerationFileIo {

    private static int n;
    private static StringBuilder res = new StringBuilder("");

    private void solve() {
        n = readInt();
        generateAllRightSeq("", 0, 0);
        out.print(res.toString());
    }

    public void generateAllRightSeq(String currSeq, int currLeft, int currRight) {
        if (currSeq.length() == 2*n) {
            //out.println(currSeq);
            res.append(currSeq);
            res.append('\n');
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
    }

    //////////////////////////////////////////////////////////////////

    long sqrtLong(long x) {
        long root = (long)Math.sqrt(x);
        while (root * root > x) --root;
        while ((root + 1) * (root + 1) <= x) ++root;
        return root;
    }

    //////////////////////////////////////////////////////////////////

    private boolean yesNo(boolean yes) {
        return yesNo(yes, "YES", "NO");
    }

    private boolean yesNo(boolean yes, String yesString, String noString) {
        out.println(yes ? yesString : noString);
        return yes;
    }

    //////////////////////////////////////////////////////////////////

    private long readLong() {
        return Long.parseLong(readString());
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
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //////////////////////////////////////////////////////////////////

    private BufferedReader in;
    private StringTokenizer tok;
    private PrintWriter out;

    private void initFileIO(String inputFileName, String outputFileName) throws FileNotFoundException {
        in = new BufferedReader(new FileReader(inputFileName));
        out = new PrintWriter(outputFileName);
    }

    private void initConsoleIO() throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    private void initIO() throws IOException {
        Locale.setDefault(Locale.US);

        String fileName = "";

        if (!fileName.isEmpty()) {
            initFileIO(fileName + ".in", fileName + ".out");
        } else {
            if (new File("input.txt").exists()) {
                initFileIO("input.txt", "output.txt");
            } else {
                initConsoleIO();
            }
        }

        tok = new StringTokenizer("");
    }

    //////////////////////////////////////////////////////////////////

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();

            initIO();
            solve();
            out.close();

            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new BracketsSequenceGenerationFileIo().run();
    }
}
