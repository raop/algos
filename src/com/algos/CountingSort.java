package com.algos;

import com.helpers.Helpers;

public class CountingSort {
    public void countingSort(Integer[] arr) {
        int countArr[] = new int[Helpers.MAX_RANDOM];
        Integer ret[] = new Integer[arr.length];

        for(int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
        }

        for(int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i -1];
        }

        for(int i = arr.length -1; i >= 0; i--) {
            int val = arr[i];
            ret[countArr[val] -1] = val;
            countArr[val]--;
        }

        for(int i =0; i < arr.length; i++) {
            arr[i] = ret[i];
        }
    }
}
