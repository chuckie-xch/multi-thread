package com.bestcode.thread.base;

/**
 * @author xch
 * @create 2017-12-19 22:10
 **/
public class ThreadWaitNotifyDemo {
    final static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run () {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1: start!");
                System.out.println(System.currentTimeMillis() + ":T1 wait for object");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1: end");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2: start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end");
                try {
                    // 模拟耗时操作
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new T1();
        Thread thread1 = new T2();
        thread.start();
        thread1.start();
    }
}
