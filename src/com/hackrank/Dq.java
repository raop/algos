package com.hackrank;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Print maximum subsection of unique numbers in a set
 */
public class Dq {

    public static void main(String[] args) {
        dq();
    }


    private static void dq() {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();

        int count = 0, maxCount = 0, maxI = 0;
        int [] arr = new int[10000001];

        for(int i = 0; i<n; i++) {
            int num = in.nextInt();
            deque.addFirst(num);

            if (i >= m) {
                int removed = deque.removeLast();
                if(arr[removed]-- == 1){
                    count--;
                }
            }

            int existingVal = arr[num]++;
            if(existingVal == 0){
                count++;
                if(count > maxCount){
                    maxCount = count;
                    maxI = i;
                }
            }

            printQueue(deque);
        }

        System.out.println("\n Maximum number of unique integers is " + maxCount + " starting at the " + maxI + " position");
    }

    private static void printQueue(Deque queue){
        System.out.println("");
        queue.stream()
                .forEach(x -> System.out.print(" -> " + x));
    }
}
