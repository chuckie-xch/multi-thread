package com.bestcode.thread.jdk.synchronous.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁申请等待时间
 *
 * @author xch
 * @create 2017-12-25 22:06
 **/
public class TryLockDemo implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.tryLock(5, TimeUnit.SECONDS);
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TryLockDemo task = new TryLockDemo();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }
}
