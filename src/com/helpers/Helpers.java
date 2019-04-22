package com.helpers;

import java.util.ArrayList;

public class Helpers {
    public static int MAX_RANDOM =100;

    public static Integer[] getRandomArray(int size, Integer... digits) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            list.add(digits.length > 0 ? getRandomInt(digits[0]) : getRandomInt());
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

    public static void printArray(Integer[] arr, int startIndex, int endIndex){
        for (int i=startIndex; i <= endIndex; i++) {
            System.out.print("[" + arr[i] + "]");
        }
        System.out.print("\n");
    }

    public static int getRandomInt() {
        Double d = Math.random() * MAX_RANDOM;
        return d.intValue();
    }

    public static int getRandomInt(int digits) {
        int ret = 0;
        for (int i =0; i < digits; i++){
            ret += Math.random() * 10 * Math.pow(10, i);
        }
        return ret;
    }

    public static Integer[] getDeltas(Integer[] arr){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1; i< arr.length; i ++){
            list.add(arr[i] - arr[i-1]);
        }
        Integer[] intArr = new Integer[arr.length - 1];
        return list.toArray(intArr);
    }

    public static void swap(Integer[] arr, Integer first, Integer second){
        int tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }
}
