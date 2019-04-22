package com.sortsearch;

import com.helpers.Helpers;

import java.util.ArrayList;

public class HeapSort {
    private int heapSize =0;

    public void heapSort(Integer[] arr){
        this.buildHeap(arr);
        this.sort(arr);
    }

    private void maxHeapify(Integer[] arr, Integer node){
        if(node < heapSize){
            int leftChild = left(node);
            int larger = node;
            if(leftChild < heapSize){
                if(arr[leftChild] > arr[node]){
                    larger = leftChild;
                }

                int rightChild = right(node);
                if(rightChild < heapSize && arr[rightChild] > arr[larger]){
                    larger = rightChild;
                }

                if(node != larger){
                    Helpers.swap(arr, larger, node);
                    maxHeapify(arr, larger);
                }
            }
        }
    }

    private void buildHeap(Integer[] arr){
        heapSize = arr.length;
        for(int i = (heapSize -1)/2; i >= 0; i--){
            maxHeapify(arr, i);
        }
    }

    private Integer[] sort(Integer[] arr){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for(int i=arr.length - 1; i >= 1; i--){
            heapSize--;
            arr[0] = arr[i];
            maxHeapify(arr, 0);
            list.add(arr[0]);
        }
        return list.toArray(arr);
    }

    // left child of arr[2] is arr[5]
    private Integer left(Integer parent){
        return 2 * parent + 1;
    }

    // right child of arr[2] is arr[6]
    private Integer right(Integer parent){
        return 2 * parent + 2;
    }

    // parent of arr[5] and arr[6] is arr[2]
    private Integer parent(Integer child){
        return child-1 / 2;
    }
}
