package com.bestcode.thread.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 可以手动设置完成状态，并实现了Future和CompletionStage的工具类
 *
 * @author xch
 * @create 2017-12-17 20:17
 **/
public class CompletableFutureDemo implements Runnable{

    CompletableFuture<Integer> result = null;

    public CompletableFutureDemo(CompletableFuture<Integer> result) {
        this.result = result;
    }

    @Override
    public void run() {
        int myResult = 0;
        try {
            myResult = result.get() * result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(myResult);
    }

    public static void main(String[] args) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        new Thread(new CompletableFutureDemo(future)).start();
        // 模拟长时间计算的过程
        Thread.sleep(1000);
        //  告知完成结果
        future.complete(60);
    }
}
