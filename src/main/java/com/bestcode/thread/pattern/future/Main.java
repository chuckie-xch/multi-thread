package com.bestcode.thread.pattern.future;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("订单已经生成");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("数据等于:" + data.getResult());
    }
}
