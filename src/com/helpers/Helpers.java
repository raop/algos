package com.helpers;

import java.util.ArrayList;

public class Helpers {

    public static Integer[] getRandomArray(int size) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < size; i++) {
            list.add(getRandomInt());
        }
        Integer[] intArr = new Integer[size];
        return list.toArray(intArr);
    }

    public static void printArray(Integer[] arr) {
        if (arr.length == 0) {
            System.out.println("[]");
        }
        for (int i=0; i < arr.length; i++) {
            System.out.print("[" + arr[i] + "]");
        }
        System.out.print("\n");
    }

    private static int getRandomInt() {
        Double d = Math.random() * 1000;
        return d.intValue();
    }
}
