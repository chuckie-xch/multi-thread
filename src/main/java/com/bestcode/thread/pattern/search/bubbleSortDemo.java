package com.bestcode.thread.pattern.search;

/**
 * 简单冒泡排序
 *
 * @author xch
 */
public class bubbleSortDemo {

    private static void dubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 52, 6, 3, 4};
        dubbleSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
