package com.bestcode.thread.pattern.search;

/**
 * 希尔排序
 *
 * @author xch
 * @create 2017-12-16 13:51
 **/
public class ShellSortDemo {

    public static void shellShort(int[] arr) {
        int length = arr.length;
        int h = 1;
        while (h <= length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = h; i < length; i++) {
                if(arr[i] < arr[i-h]) {
                    int temp = arr[i];
                    int j = i - h;
                    while (j >=0 && arr[j] > temp) {
                        arr[j + h] = arr[j];
                        j -= h;
                    }
                    arr[j + h] = temp;
                }
            }
            // 计算下一个H值
            h = (h - 1) / 3;
        }
    }
}
