package com.mycompany.difftasks.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

public class PingPong implements Runnable {
    private static final AtomicBoolean IS_PING = new AtomicBoolean(true);
    private static int STEP_COUNT = 10;

    private final String myMessage;

    public PingPong(String myMessage) {
        this.myMessage = myMessage;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread pingThread = new Thread(new PingPong("Ping"), "Ping_Thread_1");
        Thread pongThread = new Thread(new PingPong("Pong"), "Pong_Thread_2");
        pingThread.start();
        pongThread.start();
        pingThread.join();
        pongThread.join();
        System.out.println("Finish!");
    }

    @Override
    public void run() {
        boolean expected = "Ping".equals(myMessage);
        for(int i = 0; i < STEP_COUNT; i++) {
            // ожидаем нужного состояния
            while (true) {
                if (IS_PING.get() == expected) {
                    System.out.println(Thread.currentThread().getName() + ", step " + i + ": " + myMessage);
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // именяем состояние на другое
            while (!IS_PING.compareAndSet(expected, !expected)) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
