CS 591 Course Project: Build a Nim Game

As a project for Computational Game Theory, a course I took in Spring 2017, I was tasked with building a Nim game. In a game of Nim, two players (or a human and a computer) must take turns removing stones from a randomized number of piles, in which each pile has the same n amount of stones. On their turn, the player may remove n>=1 stones from any pile. The winner of the game is the player to take the last stone from the last pile.

In my version of the game, the game board is represented in this manner:

|*|O|O|*|*|*|O|O|*|O|
|*|O|O|*|*|*|O|O|*|O|   O represents a stone
|*|O|O|*|*|*|O|O|*|O|   * represents an empty space
|*|O|O|*|O|*|O|O|*|O|
|*|O|O|*|O|*|O|O|*|O|   the number of rows is randomized: 3 <= x <= 10
|O|O|O|*|O|*|O|O|*|O|   there are always 10 columns, but randomized 1 <= n <= 10 stones in each
|O|O|O|*|O|*|O|O|*|O|
|O|O|O|*|O|O|O|O|*|O|
|O|O|O|*|O|O|O|O|O|O|
|O|O|O|O|O|O|O|O|O|O|
---------------------

To run the game, open and run NimGame.java with the other files in its same folder.
