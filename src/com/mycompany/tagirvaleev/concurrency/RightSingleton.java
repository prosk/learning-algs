package com.mycompany.tagirvaleev.concurrency;

public class RightSingleton {

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 50; i++) {
            Thread t1 = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " " + Singleton.getInstance().y);
            });
            Thread t2 = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " " + Singleton.getInstance().y);
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }
    }

    private static class Singleton {
        private static /*volatile*/ Singleton INSTANCE;

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
