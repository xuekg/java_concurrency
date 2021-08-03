package com.luban.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuekg
 * @description
 * @date 2021/8/2 17:36
 **/
public class FGCDemo3 {
    public static void main(String[] args) throws InterruptedException {
        List<Object> caches = new ArrayList<Object>();
        for (int i = 0; i < 7; i++) {
            caches.add(new byte[1024 * 1024 * 3]);
        }
        caches.clear();
        for (int i = 0; i < 2; i++) {
            caches.add(new byte[1024 * 1024 * 3]);
        }
        Thread.sleep(10000);
    }
}
