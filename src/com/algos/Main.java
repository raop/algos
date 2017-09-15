package com.algos;

import com.helpers.Helpers;

public class Main {
    private static int SIZE = 200;

    public static void main(String[] args){
        Main x = new Main();
        x.search();
    }

    private Integer[] sort(){
        System.out.println("Merge Sort");

        Integer arr[] = Helpers.getRandomArray(SIZE);
        System.out.println("Before:");
        Helpers.printArray(arr);

        MergeSort m = new MergeSort();
        m.mergeSort(arr, 0, SIZE/2 -1, SIZE -1);

        System.out.println("After:");
        Helpers.printArray(arr);

        return arr;
    }

    private void search() {
        System.out.println("Binary search");

        Integer[] arr = sort();

        BinarySearch b = new BinarySearch();
        b.setSearchValue(79);
        boolean found = b.search(arr, 0, SIZE -1);
        System.out.println(b.getSearchValue() + " was " + (found ? "" : "not ") + "found in the array");
    }
}
