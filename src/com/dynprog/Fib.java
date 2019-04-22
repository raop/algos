package com.dynprog;

import java.util.stream.IntStream;

/**
 * Fibonacci implementation via Dynamic Programming
 */
public class Fib {

    public static void main(String[] args) {

        long before = System.nanoTime();
        IntStream.range(0, 50)
                .forEach(x -> System.out.println("Fibonacci of " + x + " is " + fib(x, new long[x+1])));
        long after = System.nanoTime();
        System.out.println(after-before + " nano seconds");
    }

    private static long fib(int num, long[] fib) {
        if(num == 0 || num == 1) {
            fib[num] = num;
        } else if (fib[num] == 0 ) {
            fib[num] = fib(num-1, fib) + fib(num -2, fib);
        }
        return fib[num];
    }
}
