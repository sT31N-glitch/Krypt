package com.sT31_N.Krypt.Index;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.io.File;
import java.nio.file.Files;

public class IndexParser {
    public File file;
    private String a;
    public IndexParser(){ // Have the User select the index file and get a reference to it for reading/writing.
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Krypt Files", "krypt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(chooser.getParent());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            System.out.println("User Opened File: " + chooser.getSelectedFile().getName());
        }
    }

    public void UpdateIndexRef(){
        try {
            a = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean CheckWordIndex(String s){
        String p_Words[] = a.split(" ", 0);
        for(int i = 0; i < p_Words.length; i++){
            if(s == p_Words[i]){
                return true;
            }
        }
        return false;
    }

    public boolean CheckNumIndex(int num){
        String p_Words[] = a.split(" ", 0);
        for(int i = 0; i < p_Words.length; i++){
            if(String.valueOf(num) == p_Words[i]){
                return true;
            }
        }
        return false;
    }

    public void UpdateIndex(String s){ // Write the provided string to the index file.
        try{
            FileWriter writer = new FileWriter(file, true);
            writer.write(s);
            writer.close();
            System.out.println("Updated Index");
        }catch(IOException ex){
            System.out.println("Failed to write to index");
            ex.printStackTrace();
        }
    }
}