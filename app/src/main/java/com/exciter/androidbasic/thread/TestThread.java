package com.exciter.androidbasic.thread;

public class TestThread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("TestThread:启动了线程");
    }

    public static void main(String[] args) {
        TestThread thread = new TestThread();
        thread.start();
    }
}
