package com.exciter.androidbasic.thread;

public class VolatileTest {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                }
            }.start();
        }

        //如果有子线程就让出资源，保证所有子线程都执行完
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(test.inc);
    }

}
