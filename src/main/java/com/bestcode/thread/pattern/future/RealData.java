package com.bestcode.thread.pattern.future;

public class RealData implements Data {

    protected final String result;

    public RealData(String param) {
        // result 的构造过程很慢
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++) {
            sb.append(param);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
