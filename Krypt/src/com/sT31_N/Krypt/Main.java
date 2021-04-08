package com.sT31_N.Krypt;

import com.sT31_N.Krypt.Index.Encryption;
import com.sT31_N.Krypt.Index.IndexParser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import static com.sT31_N.Krypt.sT31_NUtilities.clearConsole;
import static com.sT31_N.Krypt.sT31_NUtilities.createGap;

public class Main {
    public static void main(String[] args) {
        // CONFIG
        boolean loadFile = false;
        boolean encrypt = true;

        IndexParser indexParser = new IndexParser(); // User selects file.

        String output = new String();

        String a = new String();
        if(loadFile){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(chooser.getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    a = new String(Files.readAllBytes(file.toPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("User Opened File: " + chooser.getSelectedFile().getName());
            }
        }else{
            System.out.println("Input String: ");
            Scanner in = new Scanner(System.in); // Get User input.
            a = in.nextLine();
        }
        String[] p_A = a.split(" ", 0); // Split string into individual words.
        System.out.println("Parsing String...");
        indexParser.UpdateIndexRef();
        createGap();

        System.out.println("Indexing");
        System.out.println("--------");
        for(int i = 0; i < p_A.length; i++){ // Iterate through all words in input.
            p_A[i] = p_A[i].toLowerCase();
            indexParser.UpdateIndexRef();
            System.out.println("Current Word: " + p_A[i]);
            boolean wordKnown = indexParser.CheckWordIndex(p_A[i]); // Check if word is known.
            System.out.println("Word Known: " + wordKnown);
            if(wordKnown == true){
                System.out.println("Word already known, skipping to next word");

            }else{
                int wordID = (int)Math.floor(Math.random()*(500000-0+1)+0); // Generate the Word ID.
                System.out.println("Potential Word ID: " + wordID);
                if(indexParser.CheckNumIndex(wordID)){ // Check if Word ID is valid.
                    System.out.println("Invalid Word ID; Taken");
                }else {
                    System.out.println("Word ID Valid");
                    System.out.println("Confirmed Word ID: " + wordID);
                    indexParser.UpdateIndex(wordID + " " + p_A[i] + "\n"); // Add Words and their corresponding Word IDs to the provided index.
                }
            }
            if(encrypt){
                output = output + " " + Encryption.EncryptWord(indexParser.file, p_A[i]);
            }
            createGap();
        }
        System.out.println(output);
    }
}