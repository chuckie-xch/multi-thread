package com.bestcode.thread.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 使用thenCompose组合多个CompletableFuture,thenCompose
 *
 * @author xch
 * @create 2017-12-17 21:10
 **/
public class CompletableFutureDemo4 {
    public static Integer cal(Integer param) {
        return param / 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> cal(50))
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> cal(i)))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        future.get();
    }
}
