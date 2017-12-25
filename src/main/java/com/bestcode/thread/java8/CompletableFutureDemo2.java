package com.bestcode.thread.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 像Future那样实现异步调用
 *
 * @author xch
 * @create 2017-12-17 20:34
 **/
public class CompletableFutureDemo2 {

    public static Integer cal(Integer param) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param * param;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> cal(50));
        /**
         * 这里如果没有使用future.get()来让主线程等待的话，因为CompletableFuture是异步调用，主线程不会等待，主线程退出，
         * 而supplyAsync使用的是系统公共的线程池ForkJoinPool.common,这里的线程都是守护线程，当主线程退出时，也会退出系统。导致执行
         * 的方法无法成功。
         */
        System.out.println(future.get());

    }
}
