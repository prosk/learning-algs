package com.mycompany.tagirvaleev.concurrency;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://www.youtube.com/watch?v=kma6T8OAQ-Q
public class BadCounter {
    /*volatile*/ int x = 0; // плохо

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10; i ++) {
            runExperiment();
        }
    }

    private static void runExperiment() throws InterruptedException {
        BadCounter c = new BadCounter();
        Runnable r = () -> {
            for(int i = 0; i < 1_000_000; i++) {
                c.x++;
            }
        };
        List<Thread> threads = Stream.generate(() -> new Thread(r))
                .limit(10).peek(Thread::start)
                .collect(Collectors.toList());
        for(Thread thread: threads) {
            thread.join();
        }
        System.out.println(c.x);
    }
}
