package com.mycompany.tagirvaleev.concurrency;

import java.util.ArrayList;
import java.util.List;

// https://www.youtube.com/watch?v=kma6T8OAQ-Q
public class Container {
    private static final List<String> list = new ArrayList<>();

    synchronized void addEntry(String s) {
        list.add(s);
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable foo = () -> {
            Container container = new Container();
            for(int i = 0; i < 100_000; i++) {
                container.addEntry("foo");
            }
        };
        List<Thread> threads = new ArrayList<>();
        for(int count = 50; count > 0; count--) {
            Thread thread = new Thread(foo);
            thread.start();
            threads.add(thread);
        }
        for(Thread thread: threads) {
            thread.join();
        }
        System.out.println(list.size());
    }
}
