/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zjf12
 */
public abstract class WordBank {
    
    // properties
    protected ArrayList<String> words = new ArrayList();
    
    // constructors
    public WordBank() {
    }
    
    // getter and setter
    public ArrayList<String> getWords() {
        return words;
    }
   
    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    // word bank initialization, read words from file
    public abstract void init();
    
    // get random word
    public String[] getRandomWord() {
        Random r = new Random();
        String [] selectWords = new String [5]; // get 5 random words
        
        for (int i=0; i<selectWords.length; i++){
            String word = words.get(r.nextInt(words.size()));
            selectWords[i] = word;
            words.remove(word); // avoid repeation
        }
        
        return selectWords;
    }
}
