package com.bestcode.thread.java8.lock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock小陷阱，Unsafe.park造成死循环
 *
 * @author xch
 * @create 2017-12-17 22:13
 **/
public class StampedLockCPUDemo {
    static Thread[] holdCpuThreads = new Thread[3];
    static final StampedLock lock = new StampedLock();

    private static class HoldCPUReadThread implements Runnable {

        @Override
        public void run() {
            long lockRead = lock.readLock();
            System.out.println(Thread.currentThread().getName() + "获得读锁");
            lock.unlockRead(lockRead);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                long readLong = lock.writeLock();
                LockSupport.parkNanos(60000000000L);
                lock.unlockWrite(readLong);
            }
        }.start();
        Thread.sleep(100);
        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i] = new Thread(new HoldCPUReadThread());
            holdCpuThreads[i].start();
        }
        Thread.sleep(10000);
        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i].interrupt();
        }

    }
}
