package com.bestcode.thread.base;

/**
 * sleep demo
 *
 * @author xch
 * @create 2017-12-19 20:38
 **/
public class ThreadSleepDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("thread is interrupted");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("aaa");
                    Thread.yield();
                }
            }
        };
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
