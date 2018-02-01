/* Author: smccree
 * Date: 18 February 2017
 * Purpose: CS 591 HW01 | Constructor for the Nim Game Board      
 */

import java.util.Random;

public class NimGameBoard {
  
  //use randomizer to choose number of columns (n) for the matrix Board (n <= 10)
  
  Random randCol = new Random();
  
  public final int row = 10; 
  public final int col = randCol.nextInt(8) + 3; 
  private int[][] board;
  
  public NimGameBoard() {
    this.board = new int[row][col]; //creating an instance of board with the given number of columns
  }

  public void addStones() {
      for(int j = 0; j < col; ++j) {  //go in reverse to make sure you are filling the board from the bottom up
        Random randStone = new Random(); //randomized number of stones n for that column (n <= row)
        int numstones = randStone.nextInt(row) + 1;
          for(int i = row - 1; i >= (row - numstones); --i) { 
            board[i][j] = 1;
        }
      }
  }
  
  public void removeStones(int x, int y) {
    //a function to remove the proper amount of stones from each column
    //x is the column number we remove the stones from, y is the number of stones being removed
                       
    if(isLegalMove(x, y) == false){
      System.err.println("Illegal move attempted. Retry.");  //prints an error message if the user attempts an illegal move
    }
    else {
      for(int i = 0; y > 0; ++i) {                              //Remove stones from a specific column, from the top down
        if(board[i][x] == 0) {
          continue;               //if there is no stone at that position, continue until you find the slot with a stone
        }
        else {
          board[i][x] = 0;
          --y;
        }
      } 
    }
  }
  
  public int stonePerCol(int x) {
    //returns the number of stones in a given column;
    int column = x; //column we are looking at
    int counter = 0;
    if(x >= col) {
      System.err.println("Column Index Out of Bounds. Please try a different column.");
      return 0;
    }
    else {
      for(int i = 0; i < row; ++i) {
        if(board[i][column] == 1) {
          ++counter;
        }
      }
    return counter;
    }
  }
  
  
  private String intToBinary(int x) {
    //a function that turns an integer into its binary representation
    return Integer.toBinaryString(x);
  }
  
  private int binaryToInt(String b) {
    //a function that turns a binary string into an integer representation
    return Integer.parseInt(b, 2);
  }

  private String NimAddition(String x, String y, String z) {
    //a function that computes the nim sum of two binary numbers
    
    String binCount = z;
    
    int lenx = x.length();
    int leny = y.length();                                                 
 
    if(lenx == 0 && leny == 0) {
      return binCount;
    }
    
    else {
      if((lenx > 0) && (leny == 0)) {
        if(x.charAt(lenx - 1) == '1') {
          binCount = "1" + binCount;
          x = x.substring(0, lenx - 1);
          return NimAddition(x, y, binCount);
        }
        
        else if(x.charAt(lenx - 1) == '0') {
          binCount = "0" + binCount;
          x = x.substring(0, lenx - 1);
          return NimAddition(x, y, binCount);
        }
      }
      
      else if(lenx == 0 && leny > 0) {
        if(y.charAt(leny - 1) == '1') {
          binCount = "1" + binCount;
          y = y.substring(0, leny - 1);
          return NimAddition(x, y, binCount);
        }
        else if(y.charAt(leny - 1) == '0') {
          binCount = "0" + binCount;
          y = y.substring(0, leny - 1);
          return NimAddition(x, y, binCount);
        }
        
      }
      else {
        if(x.charAt(lenx - 1) == '1' && y.charAt(leny - 1) == '1') {
          binCount = "0" + binCount;
          x = x.substring(0, lenx - 1);
          y = y.substring(0, leny - 1);
          return NimAddition(x, y, binCount);
        }
        else if(x.charAt(lenx - 1) == '0' && y.charAt(leny - 1) == '0') {
          binCount = "0" + binCount;
          x = x.substring(0, lenx - 1);
          y = y.substring(0, leny - 1);
          return NimAddition(x, y, binCount);
        }
        else if(x.charAt(lenx - 1) == '1' && y.charAt(leny - 1) == '0') {
          binCount = "1" + binCount;
          x = x.substring(0, lenx - 1);
          y = y.substring(0, leny - 1);
          return NimAddition(x, y, binCount);
        }
        else if(x.charAt(lenx - 1) == '0' && y.charAt(leny - 1) == '1') {
          binCount = "1" + binCount;
          x = x.substring(0, lenx - 1);
          y = y.substring(0, leny - 1);
          return NimAddition(x, y, binCount);
        }
      }
    }
    return binCount;
  }
  
  public int NimSum(String[] numbers) {
    //computes the Nim Sum of a set of binary numbers
    String num = numbers[0];
    
    String count = "";
    for(int i = 1; i < col; ++i) {
      num = NimAddition(num, numbers[i], count);
    }
    int decNum = binaryToInt(num);

    return decNum;
  }
  
  public String checkPosition() { 
    //checks whether the current board is in an N position or a P position
    
    int[] counter = new int[10]; 
    
    for(int j = 0; j < col; ++j) {
      counter[j] = stonePerCol(j);  //create an array containing the value of stonePerCol for each column in board
    }
    String[] binNums = new String[10]; 

    for(int i = 0; i < col; ++i) {
      binNums[i] = intToBinary(counter[i]);  //concatenate an array of binary strings
    }

    if(NimSum(binNums) == 0) {
      return "P";                 //a NimGame is in a P position when the nim sum of the columns = 0
    }
    
    return "N";                   //otherwise a NimGame is in an N position
  }
  
  public int[] nextPposition() {
    //finds the nearest P position of the board
    int[] counter = new int[10];
    int[] move = new int[2]; //holds the tuple that is the next P position
    
    int numCol = 0;
    int numStones = 0;
    
    for(int j = 0; j < col; ++j) {
      counter[j] = stonePerCol(j);  //create a list containing the value of stonePerCol for each column in board
    }
    String[] binNums = new String[10]; 

    for(int a = 0; a < col; ++a) {
      binNums[a] = intToBinary(counter[a]);  //concatenate a list of binary strings
    }
    int nimSum = NimSum(binNums);
    
    while(nimSum != 0) {
      for(int j = 0; j < counter.length; ++j) {     //cycling through each column
        for(int k = 1; k <= counter[j]; ++k) {    //subtracting k stones (from 1 - number of stones in the column) from j
          
          counter[j] = (counter[j] - k);
          
          for(int b = 0; b < col; ++b) {
            binNums[b] = intToBinary(counter[b]);  //concatenate a list of binary strings
          }

          nimSum = NimSum(binNums);   //nim sum of the board that resulted from subtracting k stones from column j
          numCol = j;
          numStones = k;  //set the values of numCol, numStones
        }
      }
    }
    
    move[0] = numCol;
    move[1] = numStones;
    return move;
  }
  
  public boolean isLegalMove(int x, int y) {
    //checks whether the player can remove said number of stones (x) from a given column (y)
    int column = x;
    int num = y;
    if(column >= col) {
      return false;
    }
    else if(stonePerCol(column) < num) {
      return false;
    }
    else if(num == 0) {
      return false;
    }
    
    return true;
  }
  
  private boolean isEmpty() {
    //A function that tests whether or not the Nim board is empty
    for(int i = 0; i < row; ++i) {
      for(int j = 0; j < col; ++j) {
        if(board[i][j] == 1) {        //if there is at least one stone on the board, it is not empty
          return false;
        }
      }
    }
    return true;
  }
  
  public boolean isWin() {
    //Tests to see whether the board is in its terminal position
    return (isEmpty() == true);
  }
  
  public void printBoard() {
    //for testing purposes--shows where there are 1s and 0s in the board[][]
    for(int i = 0; i < row; ++i) {
      for(int j = 0; j < col; ++j) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println("");
    }
  }
  
  public void StrBoard() {  //string representation of the board
    String stone = "O"; //stones are represented by this shape
    String blank = "*"; //lack of stone is represented by an asterisk
    
    System.out.println("Current Board:\n");
    
    //cycle through board[][] adding stones and blanks where they should exist
    for(int i = 0; i < row; ++i) {
      if(i > 0) {
        System.out.println("");
      }
      for(int j = 0; j < col; ++j) {
        if(j == 0 && board[i][j] == 1) {
          System.out.print("| " + stone + " | ");
        }
        else if(j == 0 && board[i][j] == 0) {                //creating the borders at the sides of the board
          System.out.print("| " + blank + " | ");
        }
        else if(board[i][j] == 1) {
          System.out.print(stone + " | ");
        }
        else if(board[i][j] == 0) {
          System.out.print(blank + " | ");
        }
      }
    }
    System.out.println("");
    
    for(int j = 0; j < col; ++j) {
      System.out.print("----");                //creating the border at the bottom of the board
    }
    
    System.out.println("");
    
    int n = 0;
    System.out.print("  " + n + "   ");
    ++n;
    
    for(int j = 1; j < col; ++j) {
      if(j == col - 1) {
        System.out.print(n);
      }
      else {
        System.out.print(n + "   ");
        ++n;
      }
    }
    System.out.print("\n");
  }
  
  
  public static void main(String args[]) {
    //Test Cases
    
    /*int testcount = 1;
    
    while(testcount <= 100) {      //Testing randomizer for board size
      NimGameBoard B = new NimGameBoard();
      System.out.println("Test " + testcount + ": " + "\nNumber of Columns: " + B.col + "\n");
      if(B.col > 10 || B.col < 3) {
        break;
      }
      ++testcount;
    }
    
    NimGameBoard B = new NimGameBoard();
    
    
    System.out.println("Test Case 1: Adding a random number of Stones using addStones()");
    B.addStones();
    B.StrBoard();
    
    System.out.println("\nTest Case 2: Removing a set number of stones from a given column using removeStones");
    System.out.println("if number of stones in that column is less than the number specified, should print an error message");
    B.removeStones(0, 4);
    B.StrBoard();
    
    System.out.println("\nTest Case 3: Testing removeStones again");
    B.removeStones(2, 3);
    B.StrBoard();
    
    System.out.println("\nTest Case 4: Testing printBoard()");
    System.out.println("prints a numeric representation of the Nim Game board");
    B.printBoard();
    
    System.out.println("\nTest Case 5: Testing isEmpty()");
    System.out.println("if every slot on the board holds a 0, the board is empty");
    System.out.println(B.isEmpty());
    
    System.out.println("\nTest Case 6: Testing isWin()");
    System.out.println("isWin() is true when isEmpty is true. Represents whether the board is in its terminal position");
    System.out.println(B.isWin());

    System.out.println("\nTest Case 7: Testing NimAddition");
    System.out.println("Should print:\n1111");
    System.out.println(B.NimAddition("101", "1010", ""));
    
    System.out.println("\nTest Case 8: Testing stonePerCol(x)");
    System.out.println("Should show the number of stones in the given column");              
    System.out.println("Number of Stones: " + B.stonePerCol(1));
    System.out.println("Number of Stones: " + B.stonePerCol(6));
    
    System.out.println("\nTest Case 9: Testing NimSum() and checkPosition()");
    System.out.println("Should print N if the Nim Sum of the board != 0, and P if it = 0");
    System.out.println("Current Position: " + B.checkPosition());
    
    System.out.println("\nTest Case 10: Testing isLegalMove()");
    System.out.println("returns false if the user tries to take either more stones than available, or from an unavailable column");
    System.out.println("Move 1: " + B.isLegalMove(2, 4));
    System.out.println("Move 2: " + B.isLegalMove(11, 1));
    
    */   //Commented out since I have finished testing
  }
}


