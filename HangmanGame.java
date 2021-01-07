/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zjf12
 */
public class HangmanGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        HangmanImage hangman = new HangmanImage(); // Create hangman object
        hangman.init(); // Read hangman image from file

        WordBank sports = new SportWords(); // Create sport words object
        

        WordBank movies = new MovieWords(); // Create movie words object
               
        
        while (true){
            System.out.println("Welcome!\nPress 's' to start game\nPress 'r' to view rules\nPress 'e' to exit");
            
            char input;
            try{
                input = in.next("[sreSRE]").charAt(0);   
            } catch (InputMismatchException e) {
                in.nextLine(); // consume useless input and free up scanner
                System.out.println("Invalid input, please enter 'S', s', 'R', 'r','E', 'e' ONLY");
                continue;
            } catch (Exception e) {
                System.out.println("An unknown error occurred, tyr again!");
                e.printStackTrace();
                continue;
            }
             
            if (Character.toString(input).equalsIgnoreCase("s")) { // start game

                while(true){
                    System.out.println("Game start! Please select word category\n '1' for Sport Words, '2' for Movie Words");
                    
                    int category;
                    try {
                        category = in.nextInt();

                        // choose category
                        if (category == 1){
                            // to sport words category
                            sports.init(); // Read words from file
                            Game game = new Game(sports, hangman);
                            game.gameStart();
                            break;
                        } else if (category == 2){
                            // to movie words category
                            movies.init(); // Read words from file 
                            Game game = new Game(movies, hangman);
                            game.gameStart();
                            break;
                        } else {
                            System.out.println("There are only 2 categories, please enter 1 or 2 only\n");
                            continue;
                        }
                    } catch (InputMismatchException e) {
                        in.nextLine(); // consume useless input and free up scanner
                        System.out.println("Invalid input, please enter NUMBER ONLY\n");
                        continue;    
                    } catch (Exception e) {
                        System.out.println("An unknown error occurred, try again!");
                        e.printStackTrace();
                        continue;
                    }
                }

            } else if (Character.toString(input).equalsIgnoreCase("r")) { // view rules
                System.out.println("Welcome to hangman game!");
                System.out.println("Guess the word with each letter shown as dashes, you have 10 chance for each word");
                System.out.println("If you successfuly guess 5 words, you win.");
                System.out.println("This game is not case sensitive, so you can enter either lower or upper case");
                System.out.println("Press any key to go back to menu");

                // press any key to return to menu
                if (in.hasNext()){
                    in.next();
                    continue;
                }

            } else if (Character.toString(input).equalsIgnoreCase("e")) { // exit the game
                System.exit(0);
            }
        }
           
    }
    
}
