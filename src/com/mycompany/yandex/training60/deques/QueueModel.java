package com.mycompany.yandex.training60.deques;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class QueueModel {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new QueueModel().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int b = readInt();
        Deque<WaitingGroup> waitingGroups = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            long elem = readInt();
            if (elem > 0) {
                waitingGroups.addLast(new WaitingGroup(elem, i));
            }
        }
        // цикл по минутам работы пункта
        long allWaitingTime = 0;
        for(int currMinute = 1; currMinute <= n; currMinute++) {
            long handled = 0;
            WaitingGroup wg = waitingGroups.peekFirst();
            while(handled < b && wg != null && wg.startMinuteNum <= currMinute) {
                long rest = b - handled;
                if (rest >= wg.restCnt) {
                    allWaitingTime += ((long)currMinute - wg.startMinuteNum + 1) * wg.restCnt;
                    handled += wg.restCnt;
                    waitingGroups.pollFirst();
                    wg = waitingGroups.peekFirst();
                } else {
                    allWaitingTime += ((long)currMinute - wg.startMinuteNum + 1) * rest;
                    handled += rest;
                    wg.restCnt -= rest;
                }
            }
            if (wg == null) break; // уже некого обслуживать
        }
        // если остались необслуженные, то их надо обработать
        // Все клиенты, которых не успели обслужить, еще минуту постоят, возмущаясь, и разойдутся
        while(!waitingGroups.isEmpty()) {
            WaitingGroup wg = waitingGroups.pollFirst();
            allWaitingTime += ((long)n + 1 - wg.startMinuteNum + 1) * wg.restCnt;
        }

        out.println(allWaitingTime);
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

    public static class WaitingGroup {
        long restCnt; // сколько людей осталось
        long startMinuteNum; // к началу какой минуты они пришли

        public WaitingGroup(long restCnt, long startMinuteNum) {
            this.restCnt = restCnt;
            this.startMinuteNum = startMinuteNum;
        }
    }
}