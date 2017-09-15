package com.algos;

import com.helpers.Helpers;

public class Main {
    private static int SIZE = 99;

    public static void main(String[] args){
        Main x = new Main();
        x.sort();
    }

    private void sort(){
        System.out.println("Merge Sort");

        Integer arr[] = Helpers.getRandomArray(SIZE);
        System.out.println("Before:");
        Helpers.printArray(arr);

        MergeSort m = new MergeSort();
        m.mergeSort(arr, 0, SIZE/2 -1, SIZE -1);

        System.out.println("After:");
        Helpers.printArray(arr);
    }
}
