/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author zjf12
 */
public class SportWords extends WordBank{
    
    // properties
    private final String fileName = "SportWords.txt";
    
    
    // constructor
    public SportWords() {
    }
    
    // method
    @Override
    public void init() {        
        try {
            File wordBank = new File(fileName);
            Scanner reader = new Scanner(wordBank);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                words.add(data);
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("There is no sport words.");
            e.printStackTrace();
        }
    }
}
