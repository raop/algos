package com.helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Helper methods for reading & writing
 */
public class Io {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<List<Integer>> readIntListFromFile(String filename) {
        List<List<Integer>> queries = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line = reader.readLine();
            while (line != null) {
                queries.add(Arrays.stream(line.trim().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList()));
                line = reader.readLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queries;
    }

    public static List<List<Integer>> readIntListFromStdin(String delimiter){
        if(delimiter == null){
            delimiter = " ";
        }

        List<List<Integer>> outList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                outList.add(Arrays.stream(line.split(delimiter)).map(Integer::parseInt).collect(toList()));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return outList;
    }


    public static int[][] readIntGridFromList(List<List<Integer>> listOfList) {
        int n = listOfList.size();
        int[][] grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            List<Integer> list = listOfList.get(i);
            for (int j = 0; j < n; j++) {
                grid[i][j] = list.get(j);
            }
        }

        return grid;
    }

    public static int[][] readIntGridFromFile(String filename) {
        List<List<Integer>> listOfList = readIntListFromFile(filename);
        return readIntGridFromList(listOfList);
    }

    public static int[][] readIntGridFromStdIn(){
        List<List<Integer>> listOfList = readIntListFromStdin(" ");
        return readIntGridFromList(listOfList);
    }

    public static Integer[] readIntArrFromStdin(){

        Integer[] arr = Arrays.stream(scanner.nextLine().replace("\\s+$", "").split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        return arr;
    }

    public static List<List<String>> readStringListFromStdin(String delimiter){
        if(delimiter == null){
            delimiter = " ";
        }

        List<List<String>> outList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                outList.add(Arrays.stream(line.split(delimiter)).collect(toList()));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return outList;
    }

    public static List<List<String>> readStringListFromFile(String filename, String delimiter) {
        if(delimiter == null){
            delimiter = " ";
        }

        List<List<String>> queries = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line = reader.readLine();
            while (line != null) {
                queries.add(Arrays.stream(line.trim().replaceAll("\\s+$", "").split(delimiter))
                        .collect(toList()));
                line = reader.readLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queries;
    }
}