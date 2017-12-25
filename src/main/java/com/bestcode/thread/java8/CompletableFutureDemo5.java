package com.bestcode.thread.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 使用thenCombine组合多个CompletableFuture
 *
 * @author xch
 * @create 2017-12-17 21:25
 **/
public class CompletableFutureDemo5 {

    public static Integer cal(Integer param) {
        return param / 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> cal(50));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> cal(25));
        CompletableFuture<Void> fu = future.thenCombine(future2, (i,j) -> (i + j))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        fu.get();

    }
}
