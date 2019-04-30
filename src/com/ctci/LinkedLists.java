package com.ctci;

import com.helpers.Io;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

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

    /*
    Remove the nth last element from a list
    If passed 3, remove the 3rd last element from the list
     */
    private static Integer nthLastElement(Node root, int n) {
        if(n <=0) {
            return -1;
        }

        Node follower = root;
        Node leader = root;

        while(n > 0 && leader !=null){
            leader = leader.next;
            n--;
        }

        if(leader == null){
            return -1;
        }

        while(leader != null){
            follower= follower.next;
            leader = leader.next;
        }

        return (Integer) follower.value;
    }


    public static void main(String[] args) {

        Node root = buildList(Io.readIntArrFromStdin());
        printList(root);
        removeDuplicatesNoSet(root);
        printList(root);
        System.out.println(nthLastElement(root, 3));
    }
}
