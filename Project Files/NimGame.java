/*Author: Sonya McCree, smccree@bu.edu
 * File: NimGame.java
 * Date: 18 February 2017
 * Purpose: CS 591 HW01, Problem 4: Build a Nim Game | This is the user interface
 */

//Game begins with n piles and m chips per pile
//GUI to allow player to enter move
//show the next move


import java.util.Scanner;

public class NimGame {
  
  private static String p1position = "N";
  private static String p2position = "P";
  
  public void NimGameIntro() {
    //This function prints the rules and introduces the Nim Game
    System.out.println("Welcome to Nim Game! To play, remove any number between 1 and all of the stones from a single pile.\nOn the board, O represents a stone, and * is an empty space.");
    System.out.println("The winner is the player who takes the last stone and clears the board.");
    System.out.println("\nCan you beat the Nim Player?\n");
  }
  
  public void whoseMove() {
    //This function keeps track of which player just went, aka whether P1 or P2 is the P/N player
    if(p1position ==  "N") {
      p1position = "P";
      p2position = "N";
    }
    else {
      p1position = "N";
      p2position = "P";
    }
  }
  
  public void nextMove(int x, int y, NimGameBoard B) {
    //calls the removeStones function in NimGameBoard x = column, y = number of stones
    B.removeStones(x, y);
    //System.out.println("");
  }
  
  public void isWinner() {
    //prints a congratulatory message for the player who cleared the board
    //if NimGamePlayer (P2) cleared the board, prints a concilatory message
    if(p1position == "N") {
      System.out.println("The winner is Player 2. Better luck next time!");
    }
    else {
      System.out.println("The winner is Player 1. Congratulations!");
    }
  }
  
  public static void main(String args[]) {
    
    NimGame N = new NimGame();
    NimGameBoard B = new NimGameBoard();
    NimGamePlayer P2 = new NimGamePlayer();
    
    B.addStones();
    
    N.NimGameIntro();
    
    Scanner userInput = new Scanner(System.in);
    
    while(B.isWin() == false) {
      B.StrBoard();
      System.out.println("\nPlayer 1's Turn:");
      
      System.out.println("\nWhich column?");
      int column = userInput.nextInt();
      System.out.println("\nHow many stones?");
      int stone = userInput.nextInt();
      
      while(B.isLegalMove(column, stone) == false) {
        System.err.println("Illegal move attempted!");
        System.out.println("Please try again:");
        System.out.println("Which column?");
        column = userInput.nextInt();
        System.out.println("How many stones?");
        stone = userInput.nextInt();
      }
      
      int[] move = new int[2];
      
      move[0] = column;
      move[1] = stone;
      
      System.out.println("You chose to take " + move[1] + " stones from column " + move[0]);
      N.nextMove(move[0], move[1], B);
      N.whoseMove();
      
      if(B.isWin() == false) {
   
        System.out.println("\nPlayer 2's Turn:");
        int[] p2move = P2.chooseMove(B);
        
        while(B.isLegalMove(p2move[0], p2move[1]) == false) {
          p2move = P2.chooseMove(B);
        }
        System.out.println("Player 2 chose to take " + p2move[1] + " stones from column " + p2move[0]);
        N.nextMove(p2move[0], p2move[1], B);
        N.whoseMove();
      }
    }
    
    userInput.close();
    System.out.println("");
    N.isWinner();
    System.out.println("Thank you for playing!");
    
  }
}
