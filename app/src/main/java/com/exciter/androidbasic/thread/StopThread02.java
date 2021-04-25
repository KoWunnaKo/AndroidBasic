package com.exciter.androidbasic.thread;

import java.util.concurrent.TimeUnit;

public class StopThread02 {

    public static void main(String[] args) throws InterruptedException {
        MoonRunner runner = new MoonRunner();
        Thread thread = new Thread(runner, "MoonThread");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        runner.cancel();
    }

    public static class MoonRunner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on) {
                i++;
                System.out.println("i=" + i);
            }
            System.out.println("stop");
        }

        public void cancel() {
            on = false;
        }
    }
}
