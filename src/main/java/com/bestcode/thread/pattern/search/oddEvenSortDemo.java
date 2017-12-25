package com.bestcode.thread.pattern.search;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 奇偶交换排序
 *
 * @author xch
 * @create 2017-12-16 11:16
 **/
public class oddEvenSortDemo {

    private static void oddEvenSort(int[] arr) {
        int exchFlag = 1;
        int start = 0;
        while (exchFlag == 1 || start == 1) {
            exchFlag = 0;
            for (int i = start; i < arr.length - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    exchFlag = 1;
                }
            }
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
    }

    static int exchFlag = 1;

    public synchronized static int getExchFlag() {
        return exchFlag;
    }

    public synchronized static void setExchFlag(int exchFlag) {
        oddEvenSortDemo.exchFlag = exchFlag;
    }

    public static class OddEvenSortTask implements Runnable {

        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    private static int[] arr = {5, 52, 6, 3, 4};
    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    private static void pOddEvenSort(int[] arr) throws InterruptedException {
        int start = 0;
        while (getExchFlag() == 1 || start == 1) {
            setExchFlag(0);
            CountDownLatch latch = new CountDownLatch(arr.length/2 - (arr.length%2==0?start: 0));

            for (int i = 0; i < arr.length - 1; i += 2) {
                pool.submit(new OddEvenSortTask(i, latch));
            }
            latch.await();
            if(start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
    }

    public static void main(String[] args) {

    }
}
