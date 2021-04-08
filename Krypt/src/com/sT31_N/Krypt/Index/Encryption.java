package com.sT31_N.Krypt.Index;

import java.io.*;

public class Encryption {
    public final static String EncryptWord(File file, String i){
        String a = new String();
        FileReader fr = null;
        try{
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load EC file!");
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;
        try{
            while(true){
                line = reader.readLine();
                if(line==null){
                    break;
                }
                if(line.contains(i)){
                    String[] p_L = line.split(" ", 2);
                    a = p_L[0];
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return a;
    }
}