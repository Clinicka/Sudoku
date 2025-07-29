// Annabelle Clinick
// CS 143
// HW Core Topics: 2D arrays, file input/output, simple validation, helper methods
//
// This program defines a MySudokuBoard class that reads a 9x9 Sudoku puzzle from a .sdk file
// and stores the board as a 2D array of characters. It can check if the board is valid and/or solved
// by checking that all entries are correct digits, and there are no duplicate
// digits in any row, column, or box.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MySudokuBoard {
   private char[][] board;

   // pre: filename must point to a valid 9x9 Sudoku file in .sdk format
   // post: initializes the board by reading the file
      public MySudokuBoard(String filename) {
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
   
   
   
      // Pre: None.
      // Post: Returns true if the board only contains valid entries and
      //       has no duplicate numbers in any row, column, or 3x3 box;
      //       returns false otherwise.
      public boolean isValid() {
         return hasValidData() && noRowDuplicates() && noColDuplicates() && noBoxDuplicates();
      }
      
   
      // Pre: None.
      // Post: Returns true if the board is valid and each digit 1-9 appears
      //       exactly nine times; returns false otherwise.
      public boolean isSolved() {
         if (!isValid()) {
            return false;
         }
         int[] count = new int[10]; // index 1-9
         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               char ch = board[i][j];
               if (ch >= '1' && ch <= '9') {
                  count[ch - '0']++;
               }
            }
         }
         for (int i = 1; i <= 9; i++) {
            if (count[i] != 9) {
               return false;
            }
         }
         return true;
      }
      
      
      // Pre: None.
      // Post: Returns true if all cells in the board are either '1'-'9', ' ', or '.';
      //       returns false otherwise.
      private boolean hasValidData() {
         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               char ch = board[i][j];
               // Allow ' ', '.' or any other "empty" symbol, plus '1' to '9'
               if (!(ch == ' ' || ch == '.' || (ch >= '1' && ch <= '9'))) {
                  return false;
               }
            }
         }
         return true;
      }

      
      
      
         
      // Pre: None.
      // Post: Returns true if there are no duplicate digits 1-9 in any row;
      //       returns false if a duplicate is found.
      private boolean noRowDuplicates() {
         for (int i = 0; i < 9; i++) {
            boolean[] seen = new boolean[10]; 
            for (int j = 0; j < 9; j++) {
               char ch = board[i][j];
               if (ch >= '1' && ch <= '9') {
                  int digit = ch - '0';
                  if (seen[digit]) {
                     return false;
                  }
                  seen[digit] = true;
               }
            }
         }
         return true;
      }
   
      // Pre: None.
      // Post: Returns true if there are no duplicate digits 1-9 in any column;
      //       returns false if a duplicate is found.
      private boolean noColDuplicates() {
         for (int j = 0; j < 9; j++) {
            boolean[] seen = new boolean[10]; // index 1-9
            for (int i = 0; i < 9; i++) {
               char ch = board[i][j];
               if (ch >= '1' && ch <= '9') {
                  int digit = ch - '0';
                  if (seen[digit]) {
                     return false;
                  }
                  seen[digit] = true;
               }
            }
         }
         return true;
      }
      
      
      // Pre: spot is an integer between 1 and 9, inclusive. 
      //      The board must be a 9x9 grid and already initialized.
      // Post: Returns a new 3x3 char array containing the values from the 
      //       specified mini-square of the board. The mini-square is determined 
      //       by the "spot" value as shown in the assignment instructions.
      private char[][] miniSquare(int spot) {
          char[][] mini = new char[3][3];
          for (int r = 0; r < 3; r++) {
              for (int c = 0; c < 3; c++) {
                  mini[r][c] = board[((spot - 1) / 3) * 3 + r][((spot - 1) % 3) * 3 + c];
              }
          }
          return mini;
      }

      // Pre: The board must be a 9x9 grid and already initialized.
      // Post: Returns true if every 3x3 mini-square contains no duplicate 
      //       digits '1' to '9' (ignoring blanks and non-digit cells).
      //       Returns false if any mini-square has duplicate digits.
      private boolean noBoxDuplicates() {
          for (int spot = 1; spot <= 9; spot++) {
              boolean[] seen = new boolean[10]; // index 1-9
              char[][] mini = miniSquare(spot);
              for (int r = 0; r < 3; r++) {
                  for (int c = 0; c < 3; c++) {
                      char ch = mini[r][c];
                      if (ch >= '1' && ch <= '9') {
                          int digit = ch - '0';
                          if (seen[digit]) {
                              return false;
                          }
                          seen[digit] = true;
                      }
                  }
              }
          }
          return true;
      }

   }
/*
   Checking empty board...passed.
 Checking incomplete, valid board...passed.
 Checking complete, valid board...passed.
 Checking dirty data board...passed.
 Checking row violating board...passed.
 Checking col violating board...passed.
 Checking row&col violating board...passed.
 Checking mini-square violating board...passed.
 **** HORRAY: ALL TESTS PASSED ****
 */