package com.mycompany.yandex.training60.deques;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Rovers {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Rovers().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int a1 = readInt();
        int b1 = readInt();
        final int a = a1 - 1;
        final int b = b1 - 1;

        int diff = Math.abs(a1 - b1);
        boolean abOnLine = (diff % 2) == 0;
        boolean aIsWaitingForB = false;
        if (!abOnLine) {
            if (diff == 1) {
                aIsWaitingForB = (a1 > b1);
            } else {
                aIsWaitingForB = (a1 < b1);
            }
        }

        int c1 = 0, d1 = 0;
        for(int i = 1; i <= 4; i++) {
            if (i != a1 && i != b1) {
                if (c1 == 0) c1 = i; else d1 = i;
            }
        }

        final int c = c1 - 1;
        final int d = d1 - 1;

        diff = Math.abs(c1 - d1);
        boolean cdOnLine = (diff % 2) == 0;
        boolean cIsWaitingForD = false;
        if (!cdOnLine) {
            if (diff == 1) {
                cIsWaitingForD = (c1 > d1);
            } else {
                cIsWaitingForD = (c1 < d1);
            }
        }

        List<List<Rover>> rovers = new ArrayList<>();
        int directions = 4;
        for(int i = 0; i < directions; i++)
            rovers.add(new ArrayList<>());

        int timeINF = 1000;

        int minStartTime = timeINF;
        for(int i = 1; i <= n; i++) {
            int dnum = readInt();
            int t = readInt();
            Rover rover = new Rover(t, i, dnum-1);
            rovers.get(dnum-1).add(rover);
            minStartTime = Math.min(minStartTime, t);
        }
        // sorting by time and adding to queues
        Deque<Rover> q0 = new ArrayDeque<>();
        Deque<Rover> q1 = new ArrayDeque<>();
        Deque<Rover> q2 = new ArrayDeque<>();
        Deque<Rover> q3 = new ArrayDeque<>();
        for(int i = 0; i < directions; i++) {
            Collections.sort(rovers.get(i));
            for(Rover rover: rovers.get(i)) {
                if (i == 0) {
                    q0.addLast(rover);
                } else if (i == 1) {
                    q1.addLast(rover);
                } else if (i == 2) {
                    q2.addLast(rover);
                } else {
                    q3.addLast(rover);
                }
            }
        }
        List<Deque<Rover>> qByDir = new ArrayList<>();
        qByDir.add(q0);
        qByDir.add(q1);
        qByDir.add(q2);
        qByDir.add(q3);

        // modeling by time from 1 to 100
        int currTime = minStartTime-1;
        int processed = 0;
        int[] ans = new int[n + 1];
        while(processed < n) {
            currTime++;
            // определяем кто уедет в момент времени currTime
            List<Rover> waitingRowers = new ArrayList<>();
            if (!q0.isEmpty() && q0.peekFirst().timeIn <= currTime) waitingRowers.add(q0.peekFirst());
            if (!q1.isEmpty() && q1.peekFirst().timeIn <= currTime) waitingRowers.add(q1.peekFirst());
            if (!q2.isEmpty() && q2.peekFirst().timeIn <= currTime) waitingRowers.add(q2.peekFirst());
            if (!q3.isEmpty() && q3.peekFirst().timeIn <= currTime) waitingRowers.add(q3.peekFirst());
            if (waitingRowers.isEmpty()) {
                continue; // на этом моменте времени нет приехавших в этот момент или ранее и еще не проехавших
            }
            // выбираем кто проедет
            // если есть главная дорога то первыми поедут они
            Rover mainRoverA = waitingRowers.stream().filter(x -> x.dnum == a).findFirst().orElse(null);
            Rover mainRoverB = waitingRowers.stream().filter(x -> x.dnum == b).findFirst().orElse(null);
            if (mainRoverA != null && mainRoverB == null) {
                processed++;
                ans[mainRoverA.number] = currTime;
                qByDir.get(a).pollFirst();
            } else if (mainRoverA == null && mainRoverB != null) {
                processed++;
                ans[mainRoverB.number] = currTime;
                qByDir.get(b).pollFirst();
            } else if (mainRoverA != null && mainRoverB != null) {
                if (abOnLine) {
                    // оба едут
                    processed += 2;
                    ans[mainRoverA.number] = currTime;
                    qByDir.get(a).pollFirst();
                    ans[mainRoverB.number] = currTime;
                    qByDir.get(b).pollFirst();
                } else {
                    if (aIsWaitingForB) {
                        // едет B
                        processed++;
                        ans[mainRoverB.number] = currTime;
                        qByDir.get(b).pollFirst();
                    } else {
                        // едет A
                        processed++;
                        ans[mainRoverA.number] = currTime;
                        qByDir.get(a).pollFirst();
                    }
                }
            } else {
                // роверов на главных дорогах нет, смотрим второстепенные
                Rover mainRoverC = waitingRowers.stream().filter(x -> x.dnum == c).findFirst().orElse(null);
                Rover mainRoverD = waitingRowers.stream().filter(x -> x.dnum == d).findFirst().orElse(null);
                if (mainRoverC != null && mainRoverD == null) {
                    processed++;
                    ans[mainRoverC.number] = currTime;
                    qByDir.get(c).pollFirst();
                } else if (mainRoverC == null && mainRoverD != null) {
                    processed++;
                    ans[mainRoverD.number] = currTime;
                    qByDir.get(d).pollFirst();
                } else if (mainRoverC != null && mainRoverD != null) {
                    if (cdOnLine) {
                        // оба едут
                        processed += 2;
                        ans[mainRoverC.number] = currTime;
                        qByDir.get(c).pollFirst();
                        ans[mainRoverD.number] = currTime;
                        qByDir.get(d).pollFirst();
                    } else {
                        if (cIsWaitingForD) {
                            // едет D
                            processed++;
                            ans[mainRoverD.number] = currTime;
                            qByDir.get(d).pollFirst();
                        } else {
                            // едет C
                            processed++;
                            ans[mainRoverC.number] = currTime;
                            qByDir.get(c).pollFirst();
                        }
                    }
                }
            }
        }
        // output
        for(int i = 1; i <= n; i++) {
            out.println(ans[i]);
        }
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

    public static class Rover implements Comparable<Rover> {
        int timeIn;
        int number;

        int dnum;

        public Rover(int timeIn, int number, int dnum) {
            this.timeIn = timeIn;
            this.number = number;
            this.dnum = dnum;
        }

        @Override
        public int compareTo(Rover o) {
            return Integer.compare(this.timeIn, o.timeIn);
        }
    }

}