package com.exciter.androidbasic.thread;

public class TestRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("TestRunnable:启动了线程");
    }

    public static void main(String[] args) {
        TestRunnable runnable = new TestRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
