package com.mycompany.yandex.training10.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class PhoneNumber {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new PhoneNumber().run();
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
        String newNumber = readString();
        String formattedNewNumber = formatPhoneNumber(newNumber);
        // out.println(newNumber + " : " + formatPhoneNumber(newNumber));
        for(int i = 0; i < 3; i++) {
            String currNum = readString();
            // out.println(currNum + " : " + formatPhoneNumber(currNum));
            boolean isEqual = formattedNewNumber.equals(formatPhoneNumber(currNum));
            out.println(isEqual ? "YES" : "NO");
        }
    }

    private String formatPhoneNumber(String input) {
        String onlyDigits = input.replaceAll("[+()-]+", "");
        String withoutCode = onlyDigits.length() > 10 ? onlyDigits.substring(1) : onlyDigits;
        return withoutCode.length() == 10 ? withoutCode : "495" + withoutCode;
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

