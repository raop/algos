package com.hackrank;

import com.helpers.Io;

/**
 * Find minimum cost to convert a 3x3 square to a magic square
 */
public class MagicSquare {

    public static boolean isMagicSquare(int[][] square) {
        int n = square.length;
        boolean[] uniq = new boolean[n * n+1];
        int magicVal = n * ((n * n) + 1) / 2;

        for(int row=0; row<n; row++){
            int rowSum = 0;
            for(int col=0; col<n; col++){
                int cell = square[row][col];
                if(uniq[cell]){
                   return false;
                } else {
                    uniq[cell] = true;
                }
                rowSum += cell;
            }
            if(magicVal != rowSum) {
                return false;
            }
        }
        return true;
    }

    private static final int n = 3;
    private static final int MAGIC_VAL = n * ((n * n) + 1) / 2;
    private static final int CENTER_VAL = MAGIC_VAL / 3;

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int maxDiff = Integer.MAX_VALUE;

        for(int i=2; i<=8; i+=2) {
            int[][] generated = new int[n][n];
            generated[0][0] = i;
            generated[1][1] = CENTER_VAL;
            generated[2][2] = MAGIC_VAL - CENTER_VAL - i;

            for(int j=2; j<=8; j+=2) {
                if(j != i && j != generated[2][2]) {
                    generated[0][2] = j;
                    generated[0][1] = MAGIC_VAL - i - j;
                    generated[2][0] = MAGIC_VAL - CENTER_VAL - j;
                    generated[1][0] = CENTER_VAL + j - i;
                    generated[2][1] = i + j - CENTER_VAL;
                    generated[1][2] = CENTER_VAL + i - j;
                }

                int squareDiff = findSquareDiff(s, generated);

                if(squareDiff < maxDiff) {
                    maxDiff = squareDiff;
                }
            }
        }

        return maxDiff;
    }

    private static int findSquareDiff(int[][] s, int[][] generated) {
        int diff = 0;
        for(int row=0; row < n; row++) {
            for(int col=0; col < n; col++) {
                diff += Math.abs(generated[row][col] - s[row][col]);
            }
        }
        return diff;
    }

    public static void main(String[] args){
        int[][] square = Io.readIntGridFromFile("C:\\Users\\pavan\\Desktop\\hackerrank test\\magicSquare.txt");
        boolean isMagicSquare = isMagicSquare(square);
        System.out.println((isMagicSquare ? "" : " NOT a ") + " Magic Square");

        System.out.println("Took " + formingMagicSquare(square) + " changes");
    }
}
