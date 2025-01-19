/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO: Specification
 */
public class Board {
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    // TODO: Specify, test, and implement in problem 2

    private final int sizeX;
    private final int sizeY;
    private final String[][] boardContent;
    private final String[][] boardState;
    private final String[][] boardDisplay;

    private final String untouched = " - ";
    private final String flag = " F ";
    private final String bomb = "b";
    private final String empty =  "n";
     
    private final String dug = "  ";

    private final Set<Position> bombPositions = new HashSet<>();

    // rows: y
    private final int[] yOffsets = {-1, -1, -1, 0, 0, +1, +1, +1};
    // columns: x
    private final int[] xOffsets = {-1, 0, +1, -1, +1, -1, 0, +1};

    public Board(int sizeX,int sizeY) {
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.boardContent = new String[sizeY][sizeX];
        this.boardState = new String[sizeY][sizeX];
        this.boardDisplay = new String[sizeY][sizeX];

        // bomb positions
        Set<Position> bombPositions = new HashSet<>();
        bombPositions.add(new Position(2, 3));
        bombPositions.add(new Position(4, 1));
        bombPositions.add(new Position(0, 0));

        // initial board configuration, with no bombs
        // row
        for (int y = 0; y < this.sizeY; y++) {
            for (int x = 0; x < this.sizeX; x++) {
                this.boardState[y][x] = untouched;
                this.boardDisplay[y][x] = untouched;
                this.boardContent[y][x] = empty;
    
                // Check if there's a bomb at (x, y)
                if (bombPositions.contains(new Position(x, y))) {
                    this.boardContent[y][x] = bomb; // Place bomb
                } else {
                    this.boardContent[y][x] = empty; // Place empty square
                }
            }
        }
    }

    public String dig(int x, int y){
        String returnMessage = "";
        if (x < 0 || y < 0) {
            returnMessage = this.returnBoard();
        } else if (y > this.sizeY || x > this.sizeX ){
            returnMessage = this.returnBoard();
        } else if (this.boardState[y][x] != untouched) {
            returnMessage = this.returnBoard();
        } else if (this.boardState[y][x] == untouched & this.boardContent[y][x] == empty) {
            this.boardState[y][x] = dug;
            this.boardDisplay[y][x] = dug;
            int numberAdjacentBombs= this.countAdjancentBombs(y,x);
            // if we have bombs, we have to show it
            if (numberAdjacentBombs != 0) {
                this.boardState[y][x] = dug;
                this.boardDisplay[y][x] = String.valueOf(numberAdjacentBombs);
            } else {
            // if we do not have bombs, we have to recursively explore it, so dig again? 
                    this.recursiveExplore(y, x);
            }
        }
        return returnMessage;
    }
    
    
    private void recursiveExplore(int y, int x) {
        if (x < 0 || y < 0) {
            // Do nothing and go back in the stack
        } else if (y > this.sizeY || x > this.sizeX ){
            // Do nothing and go back in the stack
        } else if (this.boardContent[y][x] == bomb) {
            // Do nothing and recursively explore
            for (int i = 0; i < yOffsets.length; i ++){
                int adjancentY = y + yOffsets[i];
                int adjancentX = x + xOffsets[i];
                this.recursiveExplore(adjancentY, adjancentX);
            }
        // check for no bomb at the position
        } else if (this.boardState[y][x] == untouched & this.boardContent[y][x] == empty) {
            this.boardState[y][x] = dug;
            this.boardDisplay[y][x] = dug;
            int numberAdjacentBombs= this.countAdjancentBombs(y,x);
            // if we have bombs, we have to show it
            if (numberAdjacentBombs != 0) {
                this.boardState[y][x] = dug;
                this.boardDisplay[y][x] = String.valueOf(numberAdjacentBombs);
        }
        }

        this.recursiveExplore(y, x);


    }

    private int countAdjancentBombs(int y, int x) {
        int numberAdjacentBombs = 0;

        for (int i = 0; i < yOffsets.length; i ++){
            int adjancentY = y + yOffsets[i];
            int adjancentX = x + xOffsets[i];

            if (this.boardContent[adjancentY][adjancentX] == bomb) { 
                numberAdjacentBombs++;
            }
        }

        return numberAdjacentBombs;
    }




    public String returnBoard() {
        StringBuilder sb = new StringBuilder(); 
        // row
        for (int y = 0; y < this.sizeY; y++) {
            // column
            for (int x = 0; x< this.sizeX; x++) {
                sb.append(this.boardDisplay[y][x]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    } 
}
