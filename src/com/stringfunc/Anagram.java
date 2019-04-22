package com.stringfunc;

/**
 * Are 2 strings anagrams of each other?
 */
public class Anagram {

    public static boolean isAnagram(String a, String b) {
        // Complete the function
        if(a.length() != b.length()) {
            return false;
        }

        int[] charArr = new int[256];
        java.util.Map<Character, Integer> charMap = new java.util.HashMap<>();
        String lowerA = a.toLowerCase();
        String lowerB = b.toLowerCase();

        for(int i=0; i<a.length(); i++){
            char currentChar = lowerA.charAt(i);
            charArr[currentChar]++;
        }

        for(int i=0; i<b.length(); i++){
            char currentChar = lowerB.charAt(i);
            int count = charArr[currentChar]--;
            if(count < 0) {
                return false;
            }
        }

        for(int i=0; i< charArr.length; i ++) {
            if (charArr[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String str1 = "magna";
        String str2 = "Nagma";
        System.out.println(Anagram.isAnagram(str1, str2) ? "Anagram" : "Not Anagram");
    }
}