package com.playfair;
import java.util.Scanner;

public class Main {
    static char[][] generateTable(String key) {
        key = key.replace("i", "j");
        String key_filtered = "";
        for (int i = 0; i<key.length(); i++){
            char c = key.charAt(i);
            if(key_filtered.indexOf(c) == -1) {
                key_filtered+=c;
            }
        }

        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < key_filtered.length(); i++) {
            //remove the key chars from alpha
            alpha = alpha.replaceAll(String.valueOf(key_filtered.charAt(i)), "");
        }

        String chars = key_filtered + alpha;
        char[][] table = new char[5][5];
        int k = 0;
        for (int i = 0; i<5; i++) {
            for(int j = 0; j<5; j++){
                table[i][j] = chars.charAt(k);
                k++;
            }
        }
        return table;
    }

    static String encrypt(String txt, String key) {

        String encrypted = "";
        char[][] table = generateTable(key);
        txt = preprocess(txt);

        for(int i = 0; i<txt.length()-1; i+=2){

            int[] xy1 = getXY(table, txt.charAt(i));
            int[] xy2 = getXY(table, txt.charAt(i+1));

            if(xy1[0] == xy2[0]){ //on the same row
                encrypted += table[xy1[0]][(xy1[1]+1)%5];
                encrypted += table[xy2[0]][(xy2[1]+1)%5];

            } else if(xy1[1] == xy2[1]) { //on the same col
                encrypted += table[(xy1[0]+1)%5][xy1[1]];
                encrypted += table[(xy2[0]+1)%5][xy2[1]];

            } else {
                encrypted += table[xy1[0]][xy2[1]];
                encrypted += table[xy2[0]][xy1[1]];
            }
        }

        return encrypted;
    }

    static String decrypt(String txt, String key) {

        String decrypted = "";
        char[][] table = generateTable(key);
        txt = preprocess(txt);

        for(int i = 0; i<txt.length()-1; i+=2){

            int[] xy1 = getXY(table, txt.charAt(i));
            int[] xy2 = getXY(table, txt.charAt(i+1));

            if(xy1[0] == xy2[0]){ //on the same row
                decrypted += table[xy1[0]][(xy1[1]-1+5)%5];
                decrypted += table[xy2[0]][(xy2[1]-1+5)%5];

            } else if(xy1[1] == xy2[1]) { //on the same col
                decrypted += table[(xy1[0]+4)%5][xy1[1]];
                decrypted += table[(xy2[0]+4)%5][xy2[1]];

            } else {
                decrypted += table[xy1[0]][xy2[1]];
                decrypted += table[xy2[0]][xy1[1]];
            }
        }

        return decrypted;
    }

    static String preprocess(String txt){

        //add X between identical chars in the same slot
        String mod;
        for(int i = 0; i<txt.length()-1; i+=2){
            if(txt.charAt(i) == txt.charAt(i+1)){
                mod = String.valueOf(txt.charAt(i)) + 'x' + txt.charAt(i+1);
                txt = txt.replace(txt.substring(i,i+2),mod);
            }
        }

        if(txt.length()%2 != 0){
            txt+='x';
        }

        return txt;
    }

    // gets the x/i and y/j of a char in the encryption table
    static int[] getXY(char[][] table, char c){
        int[] xy = new int[2];
        for(int i = 0 ; i < 5 ; i++){
            int j = new String(table[i]).indexOf(c);
            if( j != -1) {  //the char is found in the row
                xy[0] = i;
                xy[1] = j;
            }
        }
        return xy;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the text");
        String txt = scan.next();
        System.out.print("Please enter the key");
        String key = scan.next();

        char[][] table = generateTable(key);
        for (int i = 0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

        while (true) {
            System.out.println("1: Encrypt");
            System.out.println("2: Decrypt");
            String input = scan.next();
            if (input.equals("1")) {
                System.out.println("Encrypted text: " + encrypt(txt, key));
                break;
            } else if (input.equals("2")) {
                System.out.println("Decrypted text: " + decrypt(txt, key));
                break;
            }
        }
    }


}










