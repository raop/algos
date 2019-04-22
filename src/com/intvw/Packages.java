package com.intvw;

import com.helpers.Helpers;
import com.helpers.Io;

import java.util.*;

/**
 *  Find pair of Packages whose sum adds up to a certain number
 *  If 2 or more pairs are found, return the Indices of the pair with the highest number
 *  [30][45][25][30][15][30]
 */
public class Packages {

    public static final int MAX_CAPACITY = 60;

    public static void main(String[] args) {

        Integer[] packages = Io.readIntArrFromStdin();
        Helpers.printArray(packages);

        Integer[] maxPair = findMaxPairInArr(packages);
        System.out.println("Max Pair is:");
        Helpers.printArray(maxPair);
    }

    private static Integer[] findMaxPairInArr(Integer[] arr){

        // Map that tracks what value is in what position
        Map<Integer, List<Integer>> posMap = getPosMap(arr);

        // Traverse through the arr and see if a complement is found, if one is found keep the one with the highest value
        Integer maxValue = -1, maxIndex = -1, otherIndex = -1;
        for(int i=0; i < arr.length; i++){
            int complement = MAX_CAPACITY - arr[i];
            List<Integer> complementIndices = posMap.get(complement);
            if(complementIndices != null) {
                for(Integer complementIndex: complementIndices){
                    if(complementIndex != i){
                        int higher = (arr[i] >= MAX_CAPACITY / 2) ? arr[i] : complement;
                        if(higher > maxValue){
                            maxValue = higher;
                            maxIndex = i;
                            otherIndex = complementIndex;
                        }
                    }
                }
            }
        }

        Integer[] maxPair = {maxIndex, otherIndex};
        return maxPair;
    }

    /* Map of every package value to a list of indices it was found in */
    private static Map<Integer, List<Integer>> getPosMap(Integer[] arr){
        Map<Integer, List<Integer>> posMap = new HashMap<>();

        for(int i=0; i< arr.length; i++) {
            Integer value = arr[i];
            if (value >= MAX_CAPACITY) {
                continue;
            }

            List<Integer> indices = posMap.get(value);
            if(indices != null && indices.size() > 2){
                continue;
            }

            if (indices == null) {
                indices = new ArrayList<>();
            }
            indices.add(i);
            posMap.put(value, indices);
        }

        return posMap;
    }
}
