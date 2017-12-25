package com.bestcode.thread.java8.atomic;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * LongAccumulator原子类可以实现任意函数操作
 *
 * @author xch
 * @create 2017-12-17 23:02
 **/
public class LongAccumulatorDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
        Thread[] ts = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            ts[i] = new Thread(){
                @Override
                public void run() {
                    Random random = new Random();
                    long value = random.nextLong();
                    accumulator.accumulate(value);
                }
            };
            ts[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            ts[i].join();
        }
        System.out.println(accumulator.longValue());
    }
}
