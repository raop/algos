package com.hackrank;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FreqCounter {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Set<Integer>> freqMap = new HashMap<>();
        List<Integer> outList = new ArrayList<>();
        queries.stream().forEach(row -> evaluate(row.get(0), row.get(1), countMap, freqMap, outList));
        for(List<Integer> row: queries) {
            evaluate(row.get(0), row.get(1), countMap, freqMap, outList);
        }
        return outList;
    }

    private static void evaluate(int symbol, int val, Map<Integer, Integer> countMap, Map<Integer, Set<Integer>> freqMap,
                                 List<Integer> outList) {
        int newFreq;
        switch(symbol) {
            // Add
            case 1 :
                newFreq = addToCountMap(countMap, val);
                incrementFreq(freqMap, newFreq, val);
                break;

            // Remove
            case 2:
                newFreq = removeFromCountMap(countMap, val);

                // If not found, don't bother updating frequencies
                if(newFreq >= 0) {
                    decrementFreq(freqMap, newFreq, val);
                }
                break;

            // Check for Freq
            case 3:
                outList.add(freqMap.get(val) == null ? 0 : 1);
                break;

            default:
                System.out.println("Invalid Symbol " + symbol + " specified");
        }
    }

    private static int addToCountMap(Map<Integer, Integer> countMap, int val) {
        Integer freq = countMap.get(val);
        int newFreq = (freq == null || freq == 0)? 1 : freq + 1;
        countMap.put(val, newFreq);
        return newFreq;
    }

    private static int removeFromCountMap(Map<Integer, Integer> countMap, int val) {
        Integer freq = countMap.get(val);
        int newFreq = (freq == null) ? -1 : freq - 1;
        if (newFreq == 0) {
            countMap.remove(val);
        } else if (newFreq > 0) {
            countMap.put(val, newFreq);
        }
        return newFreq;
    }

    private static void incrementFreq(Map<Integer, Set<Integer>> freqMap, int newFreq, int val) {
        addToSet(freqMap, newFreq, val);

        // Also need to remove it from the old freq Map
        if(newFreq > 1) {
            removeFromSet(freqMap, newFreq - 1, val);
        }
    }

    private static void decrementFreq(Map<Integer, Set<Integer>> freqMap, int newFreq, int val) {
        if(newFreq > 0) {
            addToSet(freqMap, newFreq, val);
        }
        removeFromSet(freqMap, newFreq + 1, val);
    }

    private static void addToSet(Map<Integer, Set<Integer>> freqMap, int newFreq, int val) {
        Set<Integer> newSet = freqMap.get(newFreq);
        if(newSet == null) {
            newSet = new HashSet<Integer>();
        }
        newSet.add(val);
        freqMap.put(newFreq, newSet);
    }

    private static void removeFromSet(Map<Integer, Set<Integer>> freqMap, int oldFreq, int val) {
        Set<Integer> oldSet = freqMap.get(oldFreq);
        oldSet.remove(val);
        if(oldSet.isEmpty()) {
            freqMap.remove(oldFreq);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\pavan\\Desktop\\hackerrank test\\freqCounts.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\pavan\\Desktop\\hackerrank test\\freqCounts.out"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
