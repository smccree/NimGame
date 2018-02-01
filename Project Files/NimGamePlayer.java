/* Author: smccree
 * File: NimGamePlayer.java
 * Date: 18 February 2017
 * Purpose: CS 591 HW01, AI for Nim Game
 */

import java.util.Random;

public class NimGamePlayer {
  
  
  
  public int[] chooseMove(NimGameBoard B) {
    //decides the next move for NimGamePlayer using nextPposition from the NimGameBoard class
    
    int[] nextMove = new int[2];
    String currentPos = B.checkPosition();

    if(currentPos == "N") {                                      //if board is in an N position, mathematically find the closest P position
      nextMove = B.nextPposition();
    }
    
    else {
      Random randcol = new Random();
      Random randstone = new Random();
  
      int col = randcol.nextInt((B.col));                       //if the board is in a P position, randomly select the next move
      int numstone = randstone.nextInt((B.stonePerCol(col)) + 1);
      nextMove[0] = col;
      nextMove[1] = numstone;
    }
    return nextMove; 
  }
  
  public static void main(String args[]) {
    /*Test cases for NimGamePlayer
    //checking that chooseMove method works correctly
    NimGamePlayer P = new NimGamePlayer(); 
    NimGameBoard B = new NimGameBoard();
    B.addStones();
    B.StrBoard();
    P.chooseMove(B);
    */
  }
}
