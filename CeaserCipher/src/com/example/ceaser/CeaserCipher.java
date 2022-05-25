package com.example.ceaser;

import java.util.Scanner;

public class CeaserCipher {
    public static String alpha = "abcdefghijklmnopqrstuvwxyz";
    static  String encrypt(int key){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Please enter the plain text!");
        String plaintxt = sc1.nextLine();
        String ciphertxt = "";
        for (int i = 0; i<plaintxt.length(); i++){
            char t = plaintxt.charAt(i);
            int index = alpha.indexOf(t);
            t = alpha.charAt((index+key)%26);
            ciphertxt=ciphertxt+t;
        }
        return ciphertxt;
    }
    static String decrypt(int key){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Please enter the cipher text!");
        String ciphrtxt = sc1.nextLine();
        String plaintxt = "";
        for (int i = 0; i<ciphrtxt.length(); i++){
            char t = ciphrtxt.charAt(i);
            int index = alpha.indexOf(t);
            int indexnew = (index-key)%26;
            if (indexnew<0){
                t = alpha.charAt(indexnew+26);
            }
            else {
                t = alpha.charAt(indexnew);
            }
            plaintxt=plaintxt+t;

        }
        return plaintxt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter 0 for encrypt 1 for decrypt -1 for exit!");
        int i = sc.nextInt();
        while (i>=0){
            if (i==0){
                System.out.println("Please enter the shift key!");
                int key = sc.nextInt();
                System.out.println(encrypt(key));
            }
            else if(i==1){
                System.out.println("Please enter the shift key!");
                int key = sc.nextInt();
                System.out.println(decrypt(key));
            }
            else{
                return;
            }
            System.out.println("Please enter 0 for encrypt 1 for decrypt -1 for exit!");
            i = sc.nextInt();
    }
    }
}
