// Annabelle Clinick
// CS 143
// HW: Sudoku Part 1 - 2D arrays, file input, toString formatting
//
// This class tests the SudokuBoard class by loading a Sudoku puzzle from a file
// and printing the board to the console.

public class PlaySudoku {
   public static void main(String[] args) {
      SudokuBoard board = new SudokuBoard("data1-1.sdk");
      System.out.println(board);
   }
}

/*
Sample Output (from data1-1.sdk):

2 . . | 1 . 5 | . . 3 
. 5 4 | . . . | 7 1 . 
. 1 . | 2 . 3 | . 8 . 
------+-------+------
6 . 2 | 8 . 7 | 3 . 4 
. . . | . . . | . . . 
1 . 5 | 3 . 9 | 8 . 6 
------+-------+------
. 2 . | 7 . 1 | . 6 . 
. 8 1 | . . . | 2 4 . 
7 . . | 4 . 2 | . . 1 
*/

