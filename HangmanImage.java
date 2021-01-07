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
public class HangmanImage {
    private int count;
    private String[] hangmanImage;
    private final String fileName = "HangmanImage.txt";
    
    // constructor

    public HangmanImage() {
    }    
    
    
    // getter and setter

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getHangmanImage() {
        return hangmanImage;
    }

    public void setHangmanImage(String[] hangmanImage) {
        this.hangmanImage = hangmanImage;
    }    
    
    // initialize image
    public void init() {
        String hangman = "";
        
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                hangman += data +"\n";
            }
            hangmanImage = hangman.split(",");
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("There is no man to be hanged, find someone.");
            e.printStackTrace();
        }
    }
    
    // display hangman image
    public void hang(int count) {
        System.out.println("\nHangman: ");
        System.out.println(hangmanImage[count]);
    }
}
