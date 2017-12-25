package com.bestcode.thread.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 函数式风格的异常处理
 *
 * @author xch
 * @create 2017-12-17 20:59
 **/
public class CompletableFutureDemo3 {

    public static Integer cal(Integer param) {
        return param / 0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> cal(50))
                .exceptionally(ex -> {
                    System.out.println(ex.toString());
                    return 0;
                }).thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        future.get();
    }
}
