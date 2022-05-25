package com.vigenere;

import java.util.Scanner;

public class Main {
    public static String alpha = "abcdefghijklmnopqrstuvwxyz";

    static String keystream (String plaintxt, String key){
        String keytext="";
        for (int i = 0; i<plaintxt.length(); i++){
            keytext += key.charAt(i%key.length());
        }
        return keytext;
    }
    static String encrypt(char matrix[][]){
        String ciphertxt = "";
        System.out.println("Please enter the plain text!");
        Scanner sc = new Scanner(System.in);
        String plaintxt = sc.nextLine();
        System.out.println("Please enter the key!");
        String key = sc.nextLine();
        String keytext = keystream(plaintxt,key);
        for (int i =0; i<plaintxt.length(); i++){
            ciphertxt+= matrix[alpha.indexOf(plaintxt.charAt(i))][alpha.indexOf(keytext.charAt(i))];
        }
        return ciphertxt;
    }
    static String decrypt(char matrix[][]){
        String plaintxt = "";
        System.out.println("Please enter the cipher text!");
        Scanner sc = new Scanner(System.in);
        String ciphertxt = sc.nextLine();
        System.out.println("Please enter the key!");
        String key = sc.nextLine();
        String keytext = keystream(ciphertxt,key);
        for (int i=0; i<ciphertxt.length(); i++){
            for (int j=0; j<alpha.length(); j++){
                if (matrix[j][alpha.indexOf(keytext.charAt(i))]==ciphertxt.charAt(i)){
                    plaintxt+=alpha.charAt(j);
                }
            }
        }
        return plaintxt;
    }
    public static void main(String[] args) {
        char [][] matrix = new char[26][26];
        for (int i = 0; i<26; i++){
            for (int j =0; j<26; j++){
                matrix[i][j]= alpha.charAt((i+j)%26);
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }


        System.out.println(encrypt(matrix));
        System.out.println(decrypt(matrix));
    }
}
