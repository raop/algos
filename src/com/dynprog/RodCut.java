package com.dynprog;

import com.helpers.Io;

import java.util.List;
import java.util.Scanner;

/**
 * Rod-cutting problem - what's the highest profit gained by cutting a rod into different sizes
 */
public class RodCut {

    public static void main( String[] args){
        List<List<Integer>> rows = Io.readIntListFromFile("C:\\Users\\pavan\\Desktop\\hackerrank test\\rodCut.in");
        Scanner scanner = new Scanner(System.in);
        int rodLength = Integer.parseInt(scanner.nextLine());
        long before = System.nanoTime();
        int maxProfit = getMaxProfit(rodLength, rows);
        System.out.println("Took " + (System.nanoTime() - before) + " nanosecs");
        System.out.println("Max profit for Rod Length " + rodLength + " is " + maxProfit);
    }

    private static int getMaxProfit(int rodLength, List<List<Integer>> rows) {
        int[] cost = new int[rows.size() + 1];
        for(List<Integer> row: rows) {
            cost[row.get(0)] = row.get(1);
        }
//        return cutRod(new int[rows.size() + 1], rodLength, cost);
        return bottomUpCutRod(new int[rows.size() + 1], rodLength, cost);
    }

    private static int cutRod(int[] cost, int size, int[] origCost) {
        if(cost[size] == 0 && size > 0) {
            int newcost = maxValueInRange(cost, size, origCost);
            cost[size] = newcost;
        }
        return cost[size];
    }

    private static int maxValueInRange(int[]cost, int size, int[] origCost) {
        int max = -1;
        for(int i=1; i <= size; i++) {
            int newVal = origCost[i] + cutRod(cost, size - i, origCost);
            if(newVal > max) {
                max = newVal;
            }
        }
        return max;
    }

    private static int bottomUpCutRod(int[] cost, int size, int[] origCost) {

        for(int i=1; i<=size; i++) {
            int max = -1;
            for(int j=1; j<=i; j++) {
                int newVal = origCost[j] + cost[i - j];
                if(newVal > max) {
                    max = newVal;
                }
            }
            cost[i] = max;
        }
        return cost[size];
    }
}

