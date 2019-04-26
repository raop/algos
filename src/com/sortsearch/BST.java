package com.sortsearch;

import com.helpers.Io;

/**
 * Determine if a list of numbers can form a BST in pre-order
 */
public class BST {

    public static void main(String[] args) {

        Integer[] arr = Io.readIntArrFromStdin();
        System.out.println(isBst(arr) ? "YES" : "NO");
    }

    private static boolean isBst(Integer[] arr) {
        Node root = buildTree(arr);
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBst(Node node, Integer min, Integer max){
        if(node == null) {
            return true;
        }

        if(node.value < min || node.value > max) {
            return false;
        }
        return isBst(node.left, min, node.value) && isBst(node.right, node.value, max);
    }

    private static Node buildTree(Integer[]arr) {
        Node root = new Node(null, arr[0]);
        Node current = root;
        for(int i=1; i< arr.length; i++) {
            Integer value = arr[i];
            if(value < current.value) {
                // Make left child
                current.left = new Node(current, value);
                current = current.left;
            } else {
                // Go up till parent that is smaller than the value
                while(current.parent != null && current == current.parent.left && current.value < value) {
                    current = current.parent;
                }
                current.right = new Node(current, value);
                current = current.right;
            }
        }

        return root;
    }

    public static class Node {
        Integer value;
        Node left;
        Node right;
        Node parent;

        Node(){}

        Node(Node par, Integer val){
            value = val;
            this.parent = par;
        }
    }
}
