/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import minesweeper.Square;

/**
 * TODO: Specification
 */
public class Board {
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    // TODO: Specify, test, and implement in problem 2

    private final int sizeX;
    private final int sizeY;
    private final String [][] board;


    public Board(int sizeX,int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new String[sizeX][sizeY];

        // initial board configuration, with no bombs
        // row
        for (int y = 0; y < this.sizeY; y++) {
            // column
            for (int x = 0; x< this.sizeX; x++) {
                Square square = new Square();
                this.board[y][x] = square;
            } 
        }
    }

    public void dig(int x, int y){
        if (x < 0 || y < 0) {

        } else if (y > this.sizeY || x > this.sizeX ){
            
        } else if ()
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        // row
        for (int y = 0; y < this.sizeY; y++) {
            // column
            for (int x = 0; x< this.sizeX; x++) {
                sb.append(this.board[y][x]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    } 
}
