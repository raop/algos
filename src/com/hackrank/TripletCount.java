package com.hackrank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Find number of triplets that are in GP (Geometric Progression)
 */
public class TripletCount {

    // Complete the countTriplets function below.
    static long countTriplets(List<Integer> arr, int r) {
        if(arr.isEmpty()) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        long tripleCount = 0;

        for(int i=1; i <1000000000; i = i * r ) {
            map.put(i, 0);
        }

        Map<Integer, Integer> second = new HashMap<>();
        Map<Integer, Integer> third = new HashMap<>();

        // Loop through list, build a Dictionary
        for(int item: arr){
            Integer firstCount = map.get(item);
            if(firstCount != null) {
                firstCount += 1;
                map.put(item, firstCount);

                int dividedOnce = item / r;
                if(map.get(dividedOnce) != null && map.get(dividedOnce) > 0) {
                    Integer secondCount = second.get(item);
                    second.put(item, secondCount == null ? 1 : secondCount + 1);

                    int dividedTwice = item / r / r;
                    if(map.get(dividedTwice) != null && map.get(dividedTwice) > 0 && second.get(dividedOnce) != null){
//                        Integer thirdCount = third.get(dividedTwice);
//                        third.put(dividedTwice, thirdCount == null ? 1 : thirdCount + 1);
                        tripleCount += second.get(dividedOnce) * map.get(dividedTwice);
                    }
                }
            }
        }

        return tripleCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                //new FileReader("C:\\Users\\pavan\\Desktop\\hackerrank test\\triplets.txt"));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        int r = Integer.parseInt(nr[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long ans = countTriplets(arr, r);

        System.out.println(String.valueOf(ans));

        bufferedReader.close();
    }
}
