package com.ctci;

import com.helpers.Io;

import java.util.HashSet;
import java.util.Set;

public class LinkedLists {

    public static class Node<T> {
        T value;
        Node next;

        Node(){}

        Node(T val){
            value = val;
        }

        void print(){
            System.out.print(value);
        }
    }

    private static Node buildList(Integer[] arr){
        Node root = new Node (arr[0]);
        Node current = root;
        for(int i=1; i< arr.length; i++){
            Node n = new Node(arr[i]);
            current.next = n;
            current = current.next;
        }
        return root;
    }

    private static void printList(Node root) {
        Node current = root;
        while (current != null){
            current.print();
            if(current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
            
        }
        System.out.println();
    }

    private static void removeDuplicates(Node root) {
        Set<Object> uniqSet = new HashSet<>();
        Node prev = root;
        Node current = root;
        while(current != null){
            if(!uniqSet.contains(current.value)){
                uniqSet.add(current.value);
                prev = current;
            } else {
                prev.next = current.next;
            }

            current = current.next;
        }
    }

    // Same functionality, but if we weren't allowed to used a temporary cache
    private static void removeDuplicatesNoSet(Node root) {

        Node prev = null;
        Node current = root.next;

        while(current != null){
            prev = root;
            while(prev != null && prev != current){

                if(prev.value == current.value && prev.next != null){
                    prev.next = prev.next.next;
                }

                prev = prev.next;
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {

        Node root = buildList(Io.readIntArrFromStdin());
        printList(root);
        removeDuplicatesNoSet(root);
        printList(root);
    }
}
