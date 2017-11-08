package com.algos;

import com.helpers.Helpers;

public class QuickSort {

    public void quickSort(Integer[] arr){
        quicksort(arr, 0, arr.length - 1);
    }

    public void quicksort(Integer[] arr, int start, int end){
        if(start < end){
            int pivot = partition(arr, start, end);
            quicksort(arr, start, pivot - 1);
            quicksort(arr, pivot + 1, end);
        }
    }

    public int partition(Integer[] arr, int start, int end){
        int pivotValue = arr[start];
        int pivotPos = start;
        for(int i = start + 1; i <= end; i++){
            if(arr[i] < pivotValue){
                pivotPos++;
                Helpers.swap(arr, i, pivotPos);
            }
        }
        Helpers.swap(arr, start, pivotPos);
        return pivotPos;
    }
}
