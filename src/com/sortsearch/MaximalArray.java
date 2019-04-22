package com.sortsearch;

/**
 * In an array, find maximum sub-array of values
 */
public class MaximalArray {
    private int max = -1;
    private int startArr = 0;
    private int endArr = 0;

    public MaximalArray(int max, int startArr, int endArr){
        this.max = max;
        this.startArr = startArr;
        this.endArr = endArr;
    }

    public void getMaximalArray(Integer[] arr, int start, int end){
        if(start == end) {
            if(arr[start] > max){
                max = arr[start];
                startArr = start;
                endArr = start;
            }
        } else {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += arr[i];
            }
            if (sum > max) {
                max = sum;
                startArr = start;
                endArr = end;
            }

            Integer mid = (start + end) / 2;
            this.getMaximalArray(arr, start, mid);
            this.getMaximalArray(arr, mid+1, end);
            this.getMaximalArrayCrossing(arr, start, mid, end);
        }
    }

    public void getMaximalArrayCrossing(Integer[] arr, int start, int mid, int end){
        int currMax = 0, maxBeforeMid = 0, maxAfterMid = 0;
        int minIndex = mid, maxIndex = mid+1;

        for (int i = mid; i >= start; i--){
            currMax+= arr[i];
            if(currMax > maxBeforeMid){
                maxBeforeMid = currMax;
                minIndex = i;
            }
        }

        currMax = 0;
        for (int j = mid+1; j <= end; j++){
            currMax+= arr[j];
            if(currMax > maxAfterMid){
                maxAfterMid = currMax;
                maxIndex = j;
            }
        }

        if((maxAfterMid + maxBeforeMid) > max){
            max = currMax;
            startArr = minIndex;
            endArr = maxIndex;
        }
    }

    public int getStartArr() {
        return startArr;
    }

    public int getEndArr() {
        return endArr;
    }
}