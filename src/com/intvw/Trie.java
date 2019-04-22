package com.intvw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Perform pattern matching
 */
public class Trie {
    public static class Node {
        String name; // TODO not sure if this is required
        Map<String, Node> childMap;
        String endpoint; // If there isn't an endpoint, this is null
        Node wildCard;

        Node() {
        }

        Node(String name, String value) {
            this.name = name;
            this.endpoint = value;
        }
    }

    private static void parseInput(List<String> config, List<String> requestPaths) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty() && !line.equals("\n")) {
                if (line.startsWith("#")) {
                    break;
                }
                config.add(line);
            }

            while ((line = reader.readLine()) != null && !line.isEmpty() && !line.equals("\n")) {
                requestPaths.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Node buildConfigTree(List<String> config) {
        // Build a root node
        Node root = new Node("", null);

        for (String line : config) {
            Node current = root;
            String[] arr = line.split(" ");
            String[] splitStr = arr[0].split("/");
            for (String str : splitStr) {
                if (str.equals("X")) {
                    if (current.wildCard == null) {
                        Node newNode = new Node("X", null);
                        current.wildCard = newNode;
                    }
                    current = current.wildCard;
                } else {
                    if (current.childMap == null) {
                        current.childMap = new HashMap<>();
                    }
                    if (current.childMap.get(str) == null) {
                        Node newNode = new Node(str, null);
                        current.childMap.put(str, newNode);
                    }
                    current = current.childMap.get(str);
                }
            }
            current.endpoint = arr[1];
        }
        return root;
    }

    private static void processRequestPaths(Node root, List<String> requestPaths) {
        for (String path : requestPaths) {
            Node current = root;
            String[] splitStr = path.split("/");
            for (String str : splitStr) {
                if (current == null) {
                    break;
                }
                if (current.childMap == null) {
                    current = current.wildCard;
                } else {
                    Node childNode = current.childMap.get(str);
                    if (childNode == null) {
                        current = current.wildCard;
                    } else {
                        current = childNode;
                    }
                }
            }

            // Check if an endpoint was found or not
            if (current != null && current.endpoint != null) {
                System.out.println(current.endpoint);
            } else {
                // Failover to case where all wildcards are checked first
                current = root;
                for (String str : splitStr) {
                    if (current == null) {
                        break;
                    }
                    if (current.wildCard != null) {
                        current = current.wildCard;
                    } else {
                        if(current.childMap == null) {
                            break;
                        }
                        current = current.childMap.get(str);
                    }
                }

                if(current != null && current.endpoint != null) {
                    System.out.println(current.endpoint);
                } else {
                    System.out.println(404);
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        List<String> config = new ArrayList<>();
        List<String> requestPaths = new ArrayList<>();

        // Read input and populate input lists
        parseInput(config, requestPaths);

        // Build a config tree and return a pointer to the root
        Node root = buildConfigTree(config);

        // Check for each request path, if it lies in the tree, or return 404 otherwise
        processRequestPaths(root, requestPaths);
    }
}