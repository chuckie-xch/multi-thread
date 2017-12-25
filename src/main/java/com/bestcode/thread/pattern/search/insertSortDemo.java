package com.bestcode.thread.pattern.search;

/**
 * 插入排序
 *
 * @author xch
 * @create 2017-12-16 12:15
 **/
public class insertSortDemo {

    public static void insertSort(int[] arr) {
        int length = arr.length;
        int key, j;
        for (int i = 1; i < length; i++) {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 52, 6, 3, 4};
        insertSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
