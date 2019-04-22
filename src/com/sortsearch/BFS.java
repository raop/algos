package com.sortsearch;

import com.helpers.Io;

import java.util.*;

/**
 * Given the neighbours for every country, find if there is a connection between given 2 countries
 * Use Breadth first search
 */
public class BFS
{
    private static Map<String, Node> allCountriesMap = new HashMap<>();
    private static Set<Node> visited = new HashSet<>();

    private static class Node
    {
        String value;
        List<Node> neighbors;
        Node visitedFrom;

        Node(String value) {
            this.value = value;
        }

        Node(String value, List<String> peers) {
            this.value = value;
            this.setNeighbors(peers);
            allCountriesMap.put(value, this);
        }

        void setNeighbors(List<String> peers){
            List<Node> neighbors = new ArrayList<>();
            for(String str: peers) {
                Node peer = allCountriesMap.get(str);
                if(peer == null){
                    peer = new Node(str);
                    allCountriesMap.put(str, peer);
                }
                neighbors.add(peer);
            }
            this.neighbors = neighbors;
        }
    }

    // loads a graph from a list of files
    private static void loadGraph(List<List<String>> lines) {
        for(List<String> line: lines){
            String name = line.remove(0);
            Node thisCountry = allCountriesMap.get(name);
            if(thisCountry != null){
                thisCountry.setNeighbors(line);
            } else {
                thisCountry = new Node(name, line);
            }
        }
    }

    // Print a route from one country to another
    private static boolean countryConnection(String start, String destination) {
        // Do a breadth first search.
        Node country = allCountriesMap.get(start);
        Queue<Node> queue = new ArrayDeque<>();
        visitCountry(country, queue);

        while(!country.value.equals(destination) && !queue.isEmpty()) {
            country = queue.remove();
            visitCountry(country, queue);
        }
        boolean isConnection = country.value.equals(destination);
        if(isConnection) {
            while(!country.visitedFrom.equals(allCountriesMap.get(start))) {
                System.out.println(country.value);
                country = country.visitedFrom;
            }
        }

        return isConnection;
    }

    private static void visitCountry(Node country, Queue<Node> queue) {
        if(!visited.contains(country) && country.neighbors != null) {
            for(Node nbr: country.neighbors){
                if(!visited.contains(nbr)) {
                    queue.add(nbr);
                    nbr.visitedFrom = country;
                }

            }
            visited.add(country);
        }
    }

    private static void listNeighbors(String countryName) {
        Node ctry = allCountriesMap.get(countryName);
        ctry.neighbors.stream().forEach(x -> System.out.println(x.value));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        loadGraph(Io.readStringListFromFile("C:\\Users\\pavan\\Desktop\\hackerrank test\\graph.in", "\t"));

        /*
        System.out.println("Enter Country:");
        String country = scanner.nextLine();
        listNeighbors(country); */

        System.out.println("Enter 2 countries. Let's see if they are connected");
        String country1 = scanner.nextLine();
        String country2 = scanner.nextLine();
        System.out.println("\nThere is " + (countryConnection(country1, country2) ? " a" : " no ") + " connection between " + country1 + " and " + country2);
    }
}
