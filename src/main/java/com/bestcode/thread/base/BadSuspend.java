package com.bestcode.thread.base;

import javafx.scene.control.TextFormatter;

/**
 * 关于suspend的理解
 *
 * @author xch
 * @create 2017-12-19 22:27
 **/
public class BadSuspend {

    public final static Object obj = new Object();
    public static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    public static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
//        Thread.sleep(100);
        t2.resume();
        t1.join();
        t2.join();
    }
}
