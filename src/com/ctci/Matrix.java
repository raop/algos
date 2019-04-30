package com.ctci;

import com.helpers.Helpers;
import com.helpers.Io;

public class Matrix {

    public static void main(String[] args){
        int[][] matrix = Io.readIntGridFromStdIn();
        // rotateMatrix(matrix);
        zeroOutMatrix(matrix);
        Helpers.printMatrix(matrix);
    }

    private static void rotateMatrix(int[][] mat) {
        int start, end, temp, n = mat.length;
        for(int i=0; i<n; i++) {
            start = i;
            end = n -1 -i;
            if(start > end) {
                break;
            }

            for(int j=start; j<end; j++) {
                temp = mat[start][j];
                mat[start][j] = mat[j][end];
                mat[j][end] = mat[end][end-j];
                mat[end][end-j] = mat[end-j][start];
                mat[end-j][start] = temp;
            }
        }
    }

    /*
    If any value in the matrix is zero, zero out that row and column
     */
    private static void zeroOutMatrix(int[][] mat){
        int noOfRows = mat.length;
        int noOfCols = mat[0].length;

        boolean[] isZeroRow = new boolean[noOfRows];
        boolean[] isZeroCol = new boolean[noOfCols];

        for(int row=0; row < noOfRows; row++) {
            if(isZeroRow[row]){
                continue;
            }
            for(int col=0; col < noOfCols; col++) {
                if(isZeroCol[col]) {
                    continue;
                }
                if(mat[row][col] == 0) {
                    isZeroRow[row] = true;
                    isZeroCol[col] = true;
                    break;
                }
            }
        }

        for(int row=0; row < noOfRows; row++) {
            for(int col=0; col < noOfCols; col++) {
                if(isZeroRow[row] || isZeroCol[col]){
                    mat[row][col] = 0;
                }
            }
        }
    }
}
