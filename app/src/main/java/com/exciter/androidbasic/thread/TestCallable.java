package com.exciter.androidbasic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {

    public static class MyTestCallable implements Callable {
        @Override
        public Object call() throws Exception {
            return "TestCallable:启动了线程";
        }
    }

    public static void main(String[] args) {
        MyTestCallable callable = new MyTestCallable();
        ExecutorService service = Executors.newScheduledThreadPool(1);
        Future future = service.submit(callable);
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
