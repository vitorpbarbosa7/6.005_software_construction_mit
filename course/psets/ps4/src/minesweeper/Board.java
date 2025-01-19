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
     
    private final String dug = "  ";
    
    private final String bomb = " B ";
    private final String empty =  dug;

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
        bombPositions.add(new Position(3, 3));

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
                    this.boardDisplay[y][x] = bomb;
                } else {
                    this.boardContent[y][x] = empty; // Place empty square
                }
            }
        }
    }

    public String dig(int x, int y){
        String returnMessage = "";
        if (x < 0 || y < 0) {
            // Do nothing and return the board
            returnMessage = "Invalid x or y \n";
            returnMessage += this.returnBoard();
        } else if (y > this.sizeY - 1  || x > this.sizeX - 1 ){
            // Do nothing and return the board
            returnMessage = "Invalid x or y \n";
            returnMessage += this.returnBoard();
        } else if (this.boardState[y][x] != untouched) {
            // Do nothing and return the board
            returnMessage = "This is not a untouched square";
            returnMessage += this.returnBoard();
        } else if (this.boardState[y][x] == untouched & this.boardContent[y][x] == empty) {
            this.boardState[y][x] = dug;
            this.boardDisplay[y][x] = dug;
            int numberAdjacentBombs= this.countadjacentBombs(y,x);
            // if we have bombs, we have to show it
            if (numberAdjacentBombs != 0) {
                this.boardState[y][x] = dug;
                this.updateBombCount(y, x, numberAdjacentBombs);
            } else {
            // if we do not have bombs, we have to recursively explore it, so dig again? 
                // recursively explore
                for (int i = 0; i < yOffsets.length; i ++){
                    int adjacentY = y + yOffsets[i];
                    int adjacentX = x + xOffsets[i];
                    boolean yCondition = (adjacentY > 0 & adjacentY < this.sizeY);
                    boolean xCondition = (adjacentX > 0 & adjacentX < this.sizeX);
                    if  (yCondition & xCondition) {
                        this.recursiveExplore(adjacentY, adjacentX);
                }
            }
            returnMessage = this.returnBoard();
        }
        } else if (this.boardContent[y][x] == bomb) {
            // change so it contains no bomb
            this.boardContent[y][x] = empty;
            this.boardState[y][x] = dug;
            this.boardDisplay[y][x] = dug;
            
            // update bombCount
            int numberAdjacentBombs= this.countadjacentBombs(y,x);
            this.updateBombCount(y, x, numberAdjacentBombs);

            returnMessage = "BOOM!\r\n";

            // TODO terminate user connection if no debug flag is configured
        }
        return returnMessage;
    }

    private void updateBombCount(int y, int x, int numberAdjacentBombs) {
            this.boardDisplay[y][x] = String.valueOf(numberAdjacentBombs);
    }
    
    
    private void recursiveExplore(int y, int x) {
        System.out.println("Stack created");
        if (this.boardState[y][x] == dug){
            // Do nothing and go back in the stack
        }
        else if (x < 0 || y < 0) {
            // Do nothing and go back in the stack
        } else if (y > this.sizeY -1 || x > this.sizeX - 1 ){
            // Do nothing and go back in the stack
        } else if (this.boardContent[y][x] == bomb) {
            // Do nothing and go back in the stack
            
        // check for no bomb at the position
        } else if (this.boardState[y][x] == untouched & this.boardContent[y][x] == empty) {
            this.boardState[y][x] = dug;
            this.boardDisplay[y][x] = dug;
            int numberAdjacentBombs= this.countadjacentBombs(y,x);
            // if we have bombs, we have to show it
            if (numberAdjacentBombs != 0) {
                this.boardState[y][x] = dug;
                this.boardDisplay[y][x] = String.valueOf(numberAdjacentBombs);
            }
            // recursively explore
            for (int i = 0; i < yOffsets.length; i ++){
                int adjacentY = y + yOffsets[i];
                int adjacentX = x + xOffsets[i];
                boolean yCondition = (adjacentY > 0 & adjacentY < this.sizeY);
                boolean xCondition = (adjacentX > 0 & adjacentX < this.sizeX);
                if  (yCondition & xCondition) {
                    this.recursiveExplore(adjacentY, adjacentX);
                }
            }
        }
    }

    private int countadjacentBombs(int y, int x) {
        int numberAdjacentBombs = 0;

        for (int i = 0; i < yOffsets.length; i ++){
            int adjacentY = y + yOffsets[i];
            int adjacentX = x + xOffsets[i];

            boolean yCondition = (adjacentY > 0 & adjacentY < this.sizeY);
            boolean xCondition = (adjacentX > 0 & adjacentX < this.sizeX);
            if  (yCondition & xCondition) {
                if (this.boardContent[adjacentY][adjacentX] == bomb) { 
                    numberAdjacentBombs++;
                }
            } else {
                // if out of bounds we must continue the loop
                continue;
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
