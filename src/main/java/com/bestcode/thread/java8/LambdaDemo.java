package com.bestcode.thread.java8;

import java.util.stream.IntStream;

/**
 * lambda表达式
 *
 * @author xch
 * @create 2017-12-16 22:28
 **/
public class LambdaDemo {

    public static class PrimUtil {
        public static boolean isPrim(int number) {
            if(number < 2) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if(number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        long count = IntStream.range(1, 100).parallel().filter(PrimUtil::isPrim).count();
        System.out.println(count);
    }
}
