package com.sortsearch;

public class LargestCommonSubsequence {

    public Integer[] lcs(Integer[] arr, int sequenceSize){
        Integer[] seq = new Integer[arr.length - sequenceSize + 1];

        seq[0] = 0;
        int i = 0, j = 0;
        for(; i < sequenceSize; i++){
            seq[0]+= arr[i];
        }

        int max = seq[0], maxIndex = 0;
        for(;i < arr.length; i++, j++){
            seq[j+1] = seq[j] - arr[i - sequenceSize] + arr[i];
            if(seq[j+1] > max){
                max = seq[j+1];
                maxIndex = j + 1;
            }
        }

        Integer[] ret = new Integer[sequenceSize];
        for(int k=0; k < sequenceSize; k++){
            ret[k] = arr[maxIndex + k];
        }
        return ret;
    }
}
