package com.bestcode.thread.pattern.future.jdk;

import java.util.concurrent.*;

public class FutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask future = new FutureTask<String>(new RealData("name"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 执行futureTask
        executorService.submit(future);
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据):" + future.get());
    }
}
