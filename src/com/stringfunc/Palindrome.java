package com.stringfunc;

/**
 * Is a string a palindrome?
 */
public class Palindrome {

    public boolean palindrome(String str){
        int start = 0, end = str.length() - 1;

        while (start < end){
            while(!isAlphabet(str.charAt(start))){
                start++;
            }
            while(!isAlphabet(str.charAt(end))){
                end--;
            }
            if(toLower(str.charAt(start)) != toLower(str.charAt(end))){
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    private char toLower(char c){
        if (c >= 'A' && c <= 'Z'){
            return (char) (c + 32);
        }
        return c;
    }

    private boolean isAlphabet(char c){
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        String str = "AB45243242CC31~#@&% ba";

        Palindrome p = new Palindrome();
        boolean isPalindrome = p.palindrome(str);
        System.out.println(str + " is "+ (!isPalindrome ? "not ": "") + "a palindrome");
    }
}
