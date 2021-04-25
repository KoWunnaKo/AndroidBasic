package com.exciter.androidbasic.thread;

import java.util.concurrent.TimeUnit;

public class StopThread01 {

    public static void main(String[] args) throws InterruptedException {
        MoonRunner runner = new MoonRunner();
        Thread thread = new Thread(runner);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        thread.interrupt();
    }

    public static class MoonRunner implements Runnable {
        private long i;

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
                System.out.println("i=" + i);
            }
        }
    }
}
