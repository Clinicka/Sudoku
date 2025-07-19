// Annabelle Clinick
// CS 143
// HW: Sudoku Part 1 - 2D arrays, file input, toString formatting
//
// This program defines a SudokuBoard class that reads a 9x9 Sudoku puzzle from a .sdk file
// and stores the board as a 2D array of characters. It also defines a toString method
// to print the board in a user-friendly format.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuBoard {
   private char[][] board;

   // pre: filename must point to a valid 9x9 Sudoku file in .sdk format
   // post: initializes the board by reading the file
      public SudokuBoard(String filename) {
      board = new char[9][9]; // create a 9x9 board
   
      try {
         Scanner input = new Scanner(new File(filename)); // open the file
   
         for (int row = 0; row < 9; row++) {
            String line = input.nextLine(); // read one line
   
            for (int col = 0; col < 9; col++) {
               board[row][col] = line.charAt(col); // store each character in the board
            }
         }
   
      } catch (FileNotFoundException e) {
         System.out.println("File not found: " + filename);
      }
   }
   
    //pre: none
   // post: returns a pretty string representation of the board
      public String toString() {
         String result = "";
         for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
               result += "------+-------+------\n";
            }
            for (int j = 0; j < 9; j++) {
               if (j % 3 == 0 && j != 0) {
                  result += "| ";
               }
               result += board[i][j] + " ";
            }
            result += "\n";
         }
         return result;
   }

   }
