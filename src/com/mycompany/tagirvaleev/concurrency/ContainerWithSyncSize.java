package com.mycompany.tagirvaleev.concurrency;

import java.util.ArrayList;
import java.util.List;

// https://www.youtube.com/watch?v=kma6T8OAQ-Q
public class ContainerWithSyncSize {
    private final List<String> list = new ArrayList<>();

    synchronized void addEntry(String s) {
        list.add(s);
    }

    synchronized int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        ContainerWithSyncSize container = new ContainerWithSyncSize();
        Runnable foo = () -> {
            for(int i = 0; i < 100_000; i++) {
                container.addEntry("foo");
            }
        };
        List<Thread> threads = new ArrayList<>();
        for(int count = 10; count > 0; count--) {
            Thread thread = new Thread(foo);
            thread.start();
            threads.add(thread);
        }
        System.out.println("Size is " + container.size());

        while(container.size() < 1_000_000) {} // не бесконечного цикла
        System.out.println("Finished!");
        System.out.println("Final Size is " + container.size());
    }
}
