package com.dynprog;

import java.io.*;
import java.util.*;

/**
 * Find Longest common substring in 2 strings
 */
public class CommonChildStr {

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {

        // read s1 into a map of chars
        int[] map1 = buildCharMap(s1);

        // read s2 into a map of chars
        int[] map2 = buildCharMap(s2);

        // anything not in either maps is set to 0
        int[] commonMap = intersectMaps(map1, map2);

        // reduce both strings to only contain common chars
        char[] reducedStr1 = reduceStr(s1, commonMap);
        char[] reducedStr2 = reduceStr(s2, commonMap);

        if(reducedStr1.length == 0) {
            return 0;
        }

        // find largest common substring
        int[][] lcsArr = new int[reducedStr1.length+1][reducedStr2.length+1];
        // int lcs = longestCommonSubstr(reducedStr1, reducedStr2, 0, 0, lcsArr);
        int lcs = lcs(reducedStr1, reducedStr2, lcsArr);
        return lcs;
    }

    private static int[] buildCharMap(String s) {
        int[] charMap = new int[256];
        int len = s.length();
        for(int i=0; i< len; i++){
            charMap[s.charAt(i)] = 1;
        }
        return charMap;
    }

    private static int[] intersectMaps(int[] map1, int[] map2) {
        int[] intersect = new int[256];
        for(int c = 'A'; c<= 'Z'; c++) {
            intersect[c] = map1[c] * map2[c];
        }
        return intersect;
    }

    private static char[] reduceStr(String s, int[] commonMap) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        for(int i=0; i< len; i++) {
            char c = s.charAt(i);
            if(commonMap[c] > 0) {
                sb.append(c);
            }
        }
        return sb.toString().toCharArray();
    }

    private static int longestCommonSubstr(char[] c1, char[] c2) {
        int maxLen = 0;
        for(int i=0; i< c1.length; i++) {
            for(int j=0; j< c2.length; j++) {
                int tempi = i, tempj = j, len = 0;

                while (tempi < c1.length && tempj < c2.length && c1[tempi++] == c2[tempj++]){
                    len++;
                }

                if(len > maxLen) {
                    maxLen = len;
                }
            }
        }
        return maxLen;
    }

    // Dynamic Programming - Longest common substring
    private static int longestCommonSubstr(char[] c1, char[] c2, int c1Index, int c2Index, int[][]lcsArr) {
        if(c1Index >= c1.length || c2Index >= c2.length){
            return 0;
        }
        if(lcsArr[c1Index][c2Index] != 0) {
            return lcsArr[c1Index][c2Index];
        }
        int ret = 0;
        if(c1[c1Index] == c2[c2Index]) {
            ret = 1 + longestCommonSubstr(c1, c2, c1Index + 1, c2Index + 1, lcsArr);
        }
        else {
            int left = longestCommonSubstr(c1, c2, c1Index + 1, c2Index, lcsArr);
            int right = longestCommonSubstr(c1, c2, c1Index, c2Index + 1, lcsArr);
            ret = (left > right ? left : right);
        }
        lcsArr[c1Index][c2Index] = ret;
        return ret;
    }

    private static int lcs(char[] c1, char[]c2, int[][]lcsArr) {
        for(int c1Index = 0; c1Index < c1.length; c1Index++) {
            for (int c2Index = 0; c2Index < c2.length; c2Index++) {
                if(c1Index == 0 || c2Index == 0) {
                    if(c1[c1Index] == c2[c2Index]) {
                        lcsArr[c1Index][c2Index] = 1;
                    } else {
                        lcsArr[c1Index][c2Index] = 0;
                    }
                }
                else {
                    if(c1[c1Index] == c2[c2Index]) {
                        lcsArr[c1Index][c2Index] = 1 + lcsArr[c1Index -1][c2Index -1];
                    }
                    else {
                        lcsArr[c1Index][c2Index] = maxOf(lcsArr[c1Index -1][c2Index], lcsArr[c1Index][c2Index -1]);
                    }
                }
            }
        }

        return lcsArr[c1.length -1][c2.length -1];
    }

    private static int maxOf(int a, int b) {
        return a > b ? a : b;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
