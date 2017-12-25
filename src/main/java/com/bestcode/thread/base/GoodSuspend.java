package com.bestcode.thread.base;

/**
 * 利用wait和notify方法在应用层面实现suspend和resume
 *
 * @author xch
 * @create 2017-12-19 22:44
 **/
public class GoodSuspend {
    public final static Object obj = new Object();

    public static class ChangeObjectThread extends Thread {
        volatile boolean isSuspend = false;
        public void suspendMe() {
            isSuspend = true;
        }
        public void resumeMe () {
            isSuspend = false;
            synchronized (this) {
                notify();
            }
        }
        @Override
        public void run () {
            while (true) {
                synchronized (this) {
                    while (isSuspend) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    synchronized (obj) {
                        System.out.println("in changeObjectThread");
                    }
                    Thread.yield();
                }
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (obj) {
                    System.out.println("in readObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread changeObjectThread = new ChangeObjectThread();
        ReadObjectThread readObjectThread = new ReadObjectThread();
        changeObjectThread.start();
        readObjectThread.start();
        Thread.sleep(1000);
        changeObjectThread.suspendMe();
        System.out.println("suspend changeObject 2 seconds");
        Thread.sleep(2000);
        System.out.println("resume changeObject");
        changeObjectThread.resumeMe();
    }
}
