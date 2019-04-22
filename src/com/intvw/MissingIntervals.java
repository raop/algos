package com.intvw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Given a list of intervals, find missing intervals
 */
public class MissingIntervals {

    public static class Range{
        int begin;
        int end;

        Range(int b, int e){
            begin = b;
            end = e;
        }
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        // Read list of intervals from input into an arr
        int[][] arr = getInput();

        // Convert that to a Linked List where each node has a 'begin' and an 'end'
        LinkedList<Range> list = buildLinkedListFromInput(arr);

        // Traverse through the Linked List to determine uncovered interval ranges
        List<List<Integer>> ranges = findUncoveredRanges(list);

        // Print uncovered interval ranges
        printRanges(ranges);
    }

    private static LinkedList<Range> buildLinkedListFromInput(int[][] arr){
        LinkedList<Range> list = new LinkedList<>();

        // Add the first item to the LinkedList
        list.addFirst(new Range(arr[0][0], arr[0][1]));

        for(int row =1; row < arr.length; row++){
            int begin = arr[row][0];
            int end = arr[row][1];

            // Get the first element in the list
            Range current = list.peekFirst();

            // If this should be the first element of the list
            if(begin <= current.end) {
                growCurrentRange(current, begin, end);
            } else {

                // This is not the first element in the list
                Iterator<Range> itr = list.iterator();
                int i=0;
                while (itr.hasNext()) {
                    current = itr.next();
                    i++;
                    if (begin < current.end) {
                        break;
                    }
                }

                // We didn't reach the end of the list, so insert in the middle
                if(begin <= current.end){
                    if(end < current.begin) {
                        list.add(i, new Range(begin, end));
                    } else {
                        growCurrentRange(current, begin, end);
                    }
                } else {
                    // Simply insert to the end of the list
                    list.addLast(new Range(begin, end));
                }
            }
        }

        return list;
    }

    private static void growCurrentRange(Range current, int begin, int end){
        if (begin < current.begin) {
            current.begin = begin;
        }
        if (end > current.end) {
            current.end = end;
        }
    }

    private static List<Integer> buildRange(int begin, int end){
        List<Integer> pair = new ArrayList<>();
        pair.add(begin);
        pair.add(end);
        return pair;
    }

    // Traverse the linked list, any gaps would be new uncovered ranges
    private static List<List<Integer>> findUncoveredRanges(LinkedList<Range> list) {
        List<List<Integer>> uncoveredRanges = new ArrayList<>();

        Iterator<Range> itr = list.iterator();
        Range prev = list.peekFirst();
        while(itr.hasNext()){
            Range current = itr.next();
            if(current.begin > prev.end){
                uncoveredRanges.add(buildRange(prev.end, current.begin));
            }
            prev = current;
        }

        return uncoveredRanges;
    }

    private static void printRanges(List<List<Integer>> ranges) {
        ranges.stream().forEach(pair -> System.out.println(pair.get(0) + " " + pair.get(1)));
    }

    /*
        Read lines from STDIN into a 2d array of integers
    */
    private static int[][] getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String[]> strList = new ArrayList<>();

        try{
            String line;
            while((line = reader.readLine()) != null && !line.equals("\n") && !line.isEmpty()){
                strList.add(line.replace("\\s$+", "").split(" "));
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        int[][] inputArr = new int[strList.size()][2];

        int i=0;
        for(String[] strArr: strList){
            inputArr[i][0] = Integer.parseInt(strArr[0]);
            inputArr[i][1] = Integer.parseInt(strArr[1]);
            i++;
        }

        return inputArr;
    }
}
