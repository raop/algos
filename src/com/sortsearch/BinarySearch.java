package com.sortsearch;

public class BinarySearch {
    private static int DEFAULT = 1;
    private int searchValue = DEFAULT;

    public boolean search(Integer[] arr, int start, int end){
        if (start >= end){
            return false;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == searchValue) {
            return true;
        } else if (searchValue < arr[mid]) {
            return search(arr, start, mid);
        } else {
            return search(arr, mid + 1, end);
        }
    }

    public void setSearchValue(int val){
        searchValue = val;
    }

    public int getSearchValue(){
        return searchValue;
    }
}
