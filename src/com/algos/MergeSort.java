package com.algos;

import java.util.ArrayList;

public class MergeSort {

    public void mergeSort(Integer[] arr, int start, int mid, int end){
        if(start < end) {
            mergeSort(arr, start, (start + mid)/2, mid);
            mergeSort(arr, mid + 1, (mid + end + 1)/2, end);
            merge(arr, start, mid, end);
            //   Helpers.printArray(arr);
        }
    }

    private static void merge(Integer[] arr, int start, int mid, int end){
        ArrayList<Integer> leftist = new ArrayList<>();
        ArrayList<Integer> rightist = new ArrayList<>();
        for(int i = start; i <= mid; i++) {
            leftist.add(arr[i]);
        }


        for(int j = mid+1; j <= end; j ++) {
            rightist.add(arr[j]);
        }

        int left = 0, right = 0;
        while(left < leftist.size() && right < rightist.size()){
            if(leftist.get(left) < rightist.get(right)){
                arr[start++] = leftist.get(left++);
            } else {
                arr[start++] = rightist.get(right++);
            }
        }

        while(left < leftist.size()){
            arr[start++] = leftist.get(left++);
        }

        while(right < rightist.size()){
            arr[start++] = rightist.get(right++);
        }
    }
}
