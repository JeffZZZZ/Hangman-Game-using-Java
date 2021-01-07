/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zjf12
 */
public class Game {
    
    // properties
    private static int level = 0;
    private WordBank wordBank;
    private HangmanImage hangman;
    private final int maxChance = 10;
    private final String letters = "abcdefghijklmnopqrstuvwxyz";
        
    Scanner in = new Scanner(System.in);
    
    
    // constructors

    public Game() {
    }

    public Game(WordBank wordBank, HangmanImage hangman) {
        this.wordBank = wordBank;
        this.hangman = hangman;
    }



    
    // getter and setter
    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Game.level = level;
    }

    public WordBank getWordBank() {
        return wordBank;
    }

    public void setWordBank(WordBank wordBank) {
        this.wordBank = wordBank;
    }



    // the logic for each level
    public boolean eachLevel(String word) {
        int count = 0; // the integer to count number of error            
        boolean toNext = false; 
        String wordLower = word.toLowerCase(); // convert the word to lower case
        char wordChar[] = wordLower.toCharArray(); // convert word to charactor array
        
        // Create three strings to store the letter banks
        String avaLetters = letters;
        String correctLetters = "";
        String wrongLetters = "";
        
        
        // convert the word to dashes
        for (int i=0; i<wordChar.length; i++) {
            
            // make sure the space between words is not converted to dash
            if (wordChar[i] != ' ') {
               wordChar[i] = '_';
            }
        }
        
        // iterate until the word is guessed or run out of 10 chances
        while(count < maxChance && !Arrays.equals(wordChar, word.toCharArray())) {
            
            // display letter bank
            System.out.println("\nAvailable Letters: ");
            System.out.println(avaLetters.toUpperCase());
            System.out.println("\nCorrect Letters: ");
            System.out.println(correctLetters.toUpperCase());
            System.out.println("\nIncorrect Letters: ");
            System.out.println(wrongLetters.toUpperCase());
            
            // display current state of the word
            if (count<maxChance){
                for (int i=0; i<wordChar.length; i++) {
                    System.out.print(wordChar[i] + " ");
                }
            }
            
            System.out.println("\n\nChances left: " + (maxChance-count));
            System.out.println("Please enter a letter");
            
            char input;
            try {
                input = in.next("[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]").toLowerCase().charAt(0);// read user input, and convert to lower case 
            } catch (InputMismatchException e) {
                in.nextLine(); // consume useless input and free up scanner
                System.out.println("Invalid input, please enter ONE LETTER ONLY");
                continue;
            } catch (Exception e) {
                System.out.println("An unknown error occurred, tyr again!");
                e.printStackTrace();
                continue;
            }
       
            // check if the word falls available letter bank, if not, ask user to re-enter
            if (correctLetters.contains(Character.toString(input))) {

                // the letter is in correct letter bank
                System.out.println("\nThis letter has been chosen and it was CORRECT, please enter another letter");
                hangman.hang(count);
                continue;

            } else if (wrongLetters.contains(Character.toString(input))) {

                // the letter is in wrong letter bank
                System.out.println("\nThis letter has been chosen and it was WRONG, please enter another letter");
                hangman.hang(count);
                continue;

            } else { //the letter is avaliable

                // check if the word contains user input
                if (wordLower.contains(Character.toString(input))) {


                    avaLetters = avaLetters.replace(input, '\0');
                    correctLetters += input;

                    /* convert dash to letter, the for loop is meant to handle 
                       such condition that the input fits more than one letter
                    */
                    for(int i=0; i<wordChar.length; i++) {
                        if (wordLower.charAt(i) == input) {
                            wordChar[i] = word.charAt(i); // do not use input here because input stores lower case only
                        }
                    }
                    System.out.println("You are correct!");

                }else{
                    avaLetters = avaLetters.replace(input, '\0');
                    wrongLetters += input;

                    count++; // count number of errors
                    System.out.println("Sorry, the letter is wrong");
                }
                hangman.hang(count); // display hangman image
            } 
        }
        
        //Check if the user win to lose in this level
        if (count == maxChance){
            System.out.println("\nYou lose! Back to main menu\n");
            toNext = false;
        } else {
            System.out.println("The word is: ");
            
            for (int i=0; i<wordChar.length; i++) {
                System.out.print(wordChar[i] + " ");
            }
            
            toNext = true;
        }
        
        return toNext;
    }
    
    
    // start the game, take user input and return results
    public void gameStart() {
        // get 5 random words from word bank
        String selectWords[] = wordBank.getRandomWord();
        
        // count number of level completed, used only to output "You win" message
        int levelComplete = 0;
        
        // iterate through all 5 words (levels)
        for (int i=0; i<selectWords.length; i++) {
            
            level = i + 1;
            System.out.println("\nCurrent Level: " + level);
            String word = selectWords[i]; // store each word
            
            //eachLevel(word);
            
            if (!eachLevel(word)) {
                break; // If the max chance is reached, break out the loop
            }
            
            levelComplete = level;
        }
        
        if (levelComplete == 5){
            System.out.println("\nYou win! Back to main menu\n");
        }
        
    }
}
