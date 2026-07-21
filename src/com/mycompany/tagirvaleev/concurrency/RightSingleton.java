package com.mycompany.tagirvaleev.concurrency;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RightSingleton {

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 1; i++) {
            Runnable r = () -> {
                for(int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + " x = "
                            + Singleton.getInstance().x + " y = " + Singleton.getInstance().y);
                }
            };
            List<Thread> threads = Stream.generate(() -> new Thread(r))
                    .limit(100).peek(Thread::start)
                    .collect(Collectors.toList());
            for(Thread thread: threads) {
                thread.join();
            }
            System.out.println("Iteration " + i + " is finished!");
        }
    }

    private static class Singleton {
        private static volatile Singleton INSTANCE;

        int x = 1;
        int y;

        private Singleton() { y = 2;}

        public static Singleton getInstance() {
            if (INSTANCE == null) {
                synchronized (Singleton.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new Singleton();
                    }
                }
            }
            return INSTANCE;
        }
    }
}
