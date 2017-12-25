package com.bestcode.thread.java8.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * 增强的乐观读写锁
 *
 * @author xch
 * @create 2017-12-17 21:45
 **/
public class StampedLockDemo {

    private double x, y;
    private final StampedLock stampedLock = new StampedLock();

    // 排他锁
    void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }
    // 只读方法
    double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        if(!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

}
