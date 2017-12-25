package com.bestcode.thread.collection.bad;

import java.util.ArrayList;

/**
 * 并发下的ArrayList
 *
 * @author xch
 * @create 2017-12-25 21:05
 **/
public class BadArrayList {

    static ArrayList<Integer> list = new ArrayList<>(10);

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
    }
}
