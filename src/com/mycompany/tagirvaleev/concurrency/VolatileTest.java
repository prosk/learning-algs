package com.mycompany.tagirvaleev.concurrency;

public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 100; i++) {
            Foo foo = new Foo();
            Thread t1 = new Thread(() -> {
                foo.x = 1;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                foo.y = 1;
            });
            Thread t2 = new Thread(() -> {
                while (foo.y != 1);
                System.out.println(foo.x);
            });
            t1.start();
            t2.start();
            // t1.join();
            t2.join();
            t1.join();
        }
    }

    private static class Foo {
        int x = 0;
        /*volatile*/ int y = 0;
    }
}
