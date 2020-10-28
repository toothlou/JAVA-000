package com.java.train.week02.gc;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class GcLogAnalysis {

    private static Random random = new Random();

    public static void main(String[] args) {

        long startTimeMills = System.currentTimeMillis();
        long timeoutMills = TimeUnit.SECONDS.toMillis(300);
        long endTimeMills = startTimeMills + timeoutMills;

        LongAdder counter = new LongAdder();

        System.out.println("正在执行...");

        int cacheSize = 2000;
        Object[] cachedGarbage = new Object[cacheSize];

        while (System.currentTimeMillis() <endTimeMills) {
            // 生成垃圾对象
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int index = random.nextInt(2 * cacheSize);
            if (index < cacheSize) {
                cachedGarbage[index] = garbage;
            }
        }

        System.out.println("执行完成！生成对象次数：" + counter.longValue());

    }

    /**
     * 生产垃圾对象
     *
     * @param max 最大长度
     * @return 垃圾对象
     */
    private static Object generateGarbage(int max) {

        int randomSize = random.nextInt(max);
        int type = randomSize % 4;
        Object result = null;
        switch (type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new byte[randomSize];
                break;
            case 3:
                StringBuilder builder = new StringBuilder();
                String randomString = "randomString-Anything";
                while (builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }
                result = builder.toString();
                break;
        }

        return result;
    }
}
