package com.mycompany.yandex.training.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.StringTokenizer;

public class SimpleSNTP {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SimpleSNTP().run();
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
        // Ввод данных
        String aTimeString = readString();
        String bTimeString = readString();
        String cTimeString = readString();

        String todayStr = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        LocalDateTime aDateTime = LocalDateTime.parse(todayStr + "T" + aTimeString,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        LocalDateTime bDateTime = LocalDateTime.parse(todayStr + "T" + bTimeString,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        LocalDateTime cDateTime = LocalDateTime.parse(todayStr + "T" + cTimeString,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        if (cDateTime.isBefore(aDateTime)) {
            cDateTime = cDateTime.plusDays(1);
        }

        long seconds = ChronoUnit.SECONDS.between(aDateTime, cDateTime);

        long halfSeconds =  ((seconds % 2) == 0) ? seconds/2 : seconds/2 + 1;

        bDateTime = bDateTime.plusSeconds(halfSeconds);

        out.println(bDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
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
