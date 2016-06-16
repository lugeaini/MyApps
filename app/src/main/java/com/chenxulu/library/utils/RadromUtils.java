package com.chenxulu.library.utils;

import java.util.ArrayList;
import java.util.Random;

public class RadromUtils {
    /**
     * 返回一定范围内 n个随机数整数
     *
     * @param num
     * @param min
     * @param max
     * @return
     */
    public static int[] randNum(int num, int min, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        while (true) {
            int rm = (rand.nextInt(max - min) + min);
            if (!list.contains(rm)) {
                list.add(rm);
                if (list.size() >= num)
                    break;
            }
        }
        int result[] = new int[num];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 返回一个小于max的随机数
     *
     * @param max
     * @return
     */
    public static int radrom(int max, int position) {
        Random random = new Random();
        int randomNum = random.nextInt(max);
        if (position == randomNum) {
            randomNum = radrom(max, position);
        }
        return randomNum;
    }
}
