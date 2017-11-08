package com.algos;

import com.helpers.Helpers;

public class Main {
    private static int ARR_SIZE = 32;
    private static int NO_OF_DIGITS = 3;

    public static void main(String[] args){
        Main x = new Main();
        //x.search();
        //x.largestCommonSubsequence();
        //x.maximalArray();
        //x.heapSort();
        //x.quickSort();
        x.countingSort();
        //x.radixSort();
        //x.palindrome();
    }

    private Integer[] mergeSort(){
        System.out.println("Merge Sort");

        Integer arr[] = Helpers.getRandomArray(ARR_SIZE);
        System.out.println("Before:");
        Helpers.printArray(arr);

        MergeSort m = new MergeSort();
        m.mergeSort(arr, 0, ARR_SIZE /2 -1, ARR_SIZE -1);

        System.out.println("After:");
        Helpers.printArray(arr);

        return arr;
    }

    private void search(){
        System.out.println("Binary search");

        Integer[] arr = mergeSort();

        BinarySearch b = new BinarySearch();
        b.setSearchValue(79);
        boolean found = b.search(arr, 0, ARR_SIZE -1);
        System.out.println(b.getSearchValue() + " was " + (found ? "" : "not ") + "found in the array");
    }

    private void maximalArray(){
        Integer arr[] = Helpers.getRandomArray(ARR_SIZE);
        Integer deltas[] = Helpers.getDeltas(arr);
        Helpers.printArray(deltas);

        MaximalArray m = new MaximalArray(-1, 0, ARR_SIZE -2);
        m.getMaximalArray(deltas, m.getStartArr(), m.getEndArr());

        System.out.println("Maximal array is: ");
        Helpers.printArray(deltas, m.getStartArr(), m.getEndArr());
    }

    private void largestCommonSubsequence(){
        Integer arr[] = Helpers.getRandomArray(ARR_SIZE);
        int sequenceSize = 3;
        System.out.println("Largest common subsequence of size " + sequenceSize + " in :");
        Helpers.printArray(arr);

        LargestCommonSubsequence l = new LargestCommonSubsequence();
        Integer largestSubsequence[] = l.lcs(arr, sequenceSize);
        System.out.println("is: ");
        Helpers.printArray(largestSubsequence);
    }

    private void heapSort(){
        Integer arr[] = Helpers.getRandomArray(ARR_SIZE);
        System.out.println("Before:");
        Helpers.printArray(arr);

        HeapSort hs = new HeapSort();
        hs.heapSort(arr);

        System.out.println("After:");
        Helpers.printArray(arr);
    }

    private void quickSort(){
        Integer arr[] = Helpers.getRandomArray(ARR_SIZE);
        System.out.println("Before:");
        Helpers.printArray(arr);

        QuickSort qs = new QuickSort();
        qs.quickSort(arr);

        System.out.println("After:");
        Helpers.printArray(arr);
    }

    private void countingSort(){
        Integer arr[] = Helpers.getRandomArray(ARR_SIZE);
        System.out.println("Before:");
        Helpers.printArray(arr);

        CountingSort cs = new CountingSort();
        cs.countingSort(arr);

        System.out.println("After:");
        Helpers.printArray(arr);
    }

    private void radixSort(){
        Integer arr[] = Helpers.getRandomArray(ARR_SIZE, NO_OF_DIGITS);
        System.out.println("Before:");
        Helpers.printArray(arr);

        QuickSort qs = new QuickSort();
        qs.quickSort(arr);

        System.out.println("After:");
        Helpers.printArray(arr);
    }

    private void palindrome(){
        String str = "AB45243242CC31~#@&% ba";

        Palindrome p = new Palindrome();
        boolean isPalindrome = p.palindrome(str);
        System.out.println(str + " is "+ (!isPalindrome ? "not ": "") + "a palindrome");
    }
}