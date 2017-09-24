package com.algos;

import com.helpers.Helpers;

public class Main {
    private static int SIZE = 10;

    public static void main(String[] args){
        Main x = new Main();
        //x.search();
        //x.largestCommonSubsequence();
        //x.maximalArray();
        x.heapSort();
        //x.palindrome();
    }

    private Integer[] mergeSort(){
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

    private void search(){
        System.out.println("Binary search");

        Integer[] arr = mergeSort();

        BinarySearch b = new BinarySearch();
        b.setSearchValue(79);
        boolean found = b.search(arr, 0, SIZE -1);
        System.out.println(b.getSearchValue() + " was " + (found ? "" : "not ") + "found in the array");
    }

    private void maximalArray(){
        Integer arr[] = Helpers.getRandomArray(SIZE);
        Integer deltas[] = Helpers.getDeltas(arr);
        Helpers.printArray(deltas);

        MaximalArray m = new MaximalArray(-1, 0, SIZE -2);
        m.getMaximalArray(deltas, m.getStartArr(), m.getEndArr());

        System.out.println("Maximal array is: ");
        Helpers.printArray(deltas, m.getStartArr(), m.getEndArr());
    }

    private void largestCommonSubsequence(){
        Integer arr[] = Helpers.getRandomArray(SIZE);
        int sequenceSize = 3;
        System.out.println("Largest common subsequence of size " + sequenceSize + " in :");
        Helpers.printArray(arr);

        LargestCommonSubsequence l = new LargestCommonSubsequence();
        Integer largestSubsequence[] = l.lcs(arr, sequenceSize);
        System.out.println("is: ");
        Helpers.printArray(largestSubsequence);
    }

    private void heapSort(){
        Integer arr[] = Helpers.getRandomArray(SIZE);
        System.out.println("Before:");
        Helpers.printArray(arr);

        HeapSort hs = new HeapSort();
        hs.heapSort(arr);

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