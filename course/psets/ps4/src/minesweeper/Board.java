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

    public final int sizeX;
    private final int sizeY;
    private final String[][] boardContent;
    private final String[][] boardState;
    private final String[][] boardDisplay;

    private final String UNTOUCHED = " - "; 
    private final String FLAGGED = " F ";
    private final String DUG = "   ";
    private final String BOMB = " B ";
    private final String EMPTY =  DUG;

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
        bombPositions.add(new Position(0, 0));
        bombPositions.add(new Position(0 , 3));
        bombPositions.add(new Position(1, 2));
        bombPositions.add(new Position(2, 0));
        bombPositions.add(new Position(2, 3));
        bombPositions.add(new Position(3, 1));
        bombPositions.add(new Position(3, 3));

        // initial board configuration, with no bombs
        // row
        for (int y = 0; y < this.sizeY; y++) {
            for (int x = 0; x < this.sizeX; x++) {
                this.boardState[y][x] = UNTOUCHED;
                this.boardDisplay[y][x] = UNTOUCHED;
                this.boardContent[y][x] = EMPTY;
    
                // Check if there's a bomb at (x, y)
                if (bombPositions.contains(new Position(y, x))) {
                    this.boardContent[y][x] = BOMB; // Place bomb
                    this.boardDisplay[y][x] = UNTOUCHED;
                } else {
                    this.boardContent[y][x] = EMPTY; // Place empty square
                }
            }
        }
    }

    public  String dig(int y, int x){
        String returnMessage = "";
        if (x < 0 || y < 0) {
            // Do nothing and return the board
            returnMessage = "Invalid x or y \n";
            returnMessage += this.returnBoard();
        } else if (y >= this.sizeY  || x >= this.sizeX ){
            // Do nothing and return the board
            returnMessage = "Invalid x or y \n";
            returnMessage += this.returnBoard();
        } else if (this.boardState[y][x] != UNTOUCHED) {
            // Do nothing and return the board
            returnMessage = "This is not a untouched square \n";
            returnMessage += this.returnBoard();
        } else if (this.boardState[y][x] == UNTOUCHED & this.boardContent[y][x] == EMPTY) {
            this.boardState[y][x] = DUG;
            this.boardDisplay[y][x] = DUG;
            int numberAdjacentBombs= this.countadjacentBombs(y,x);
            // if we have bombs, we have to show it
            if (numberAdjacentBombs != 0) {
                this.updateBombCount(y, x, numberAdjacentBombs);
                returnMessage = this.returnBoard();
            } else {
            // if we do not have bombs, we have to recursively explore it, so dig again? 
                // recursively explore
                for (int i = 0; i < yOffsets.length; i ++){
                    int adjacentY = y + yOffsets[i];
                    int adjacentX = x + xOffsets[i];
                    this.recursiveExplore(adjacentY, adjacentX);
                // }
                }
                returnMessage = this.returnBoard();
            }
        } else if (this.boardContent[y][x] == BOMB) {
            // change so it contains no bomb
            this.boardContent[y][x] = EMPTY;
            this.boardState[y][x] = DUG;
            this.boardDisplay[y][x] = DUG;
            
            // update bombCount
            int numberAdjacentBombs= this.countadjacentBombs(y,x);
            this.updateBombCount(y, x, numberAdjacentBombs);

            // must also update bomb count for adjacent cells
            for (int i = 0; i < yOffsets.length; i ++){
                int adjacentY = y + yOffsets[i];
                int adjacentX = x + xOffsets[i];
                numberAdjacentBombs = this.countadjacentBombs(adjacentY, adjacentX) ;
                this.updateBombCount(adjacentY, adjacentX, numberAdjacentBombs);
            }

            returnMessage = Constants.BOOM; 

            // TODO terminate user connection if no debug flag is configured
        }
        return returnMessage;
    }

    public  String flag(int y, int x) {
        String returnMessage = "NOT UPDATED BOARD";

        if (this.positionCondition(y, x)) {
            if (this.boardState[y][x] == UNTOUCHED) {
                this.boardDisplay[y][x] = FLAGGED;
                this.boardState[y][x] = FLAGGED;
            }
        }
        returnMessage = this.returnBoard();
        
        return returnMessage;
    }
    public  String deflag(int y, int x) {
        String returnMessage = "NOT UPDATED BOARD";

        if (this.positionCondition(y, x)) {
            if (this.boardState[y][x] == FLAGGED) {
                this.boardDisplay[y][x] = UNTOUCHED;
                this.boardState[y][x] = UNTOUCHED;
            }
        }
        returnMessage = this.returnBoard();
        
        return returnMessage;
    }

    private void updateBombCount(int y, int x, int numberAdjacentBombs) {
            this.boardDisplay[y][x] = " " + String.valueOf(numberAdjacentBombs) + " ";
    }
    
    
    private void recursiveExplore(int y, int x) {
        System.out.println("Stack created");
        System.out.println(y); 
        System.out.println(x); 
        this.debugDisplay(y, x);
        // first conditions must be the positions, to go back doing nothing
        if (x < 0 || y < 0) {
            // Do nothing and go back in the stack
        } else if (y >= this.sizeY || x >= this.sizeX ){
            // Do nothing and go back in the stack
        } else if (this.boardState[y][x] == DUG){
            // Do nothing and go back in the stack
        } else if (this.boardContent[y][x] == BOMB) {
            // Do nothing and go back in the stack
            
        // check for no bomb at the position
        } else if (this.boardState[y][x] == UNTOUCHED & this.boardContent[y][x] == EMPTY) {
            System.out.println("entrou aqui");
            this.boardState[y][x] = DUG;
            this.boardDisplay[y][x] = DUG;
            int numberAdjacentBombs= this.countadjacentBombs(y,x);
            // if we have bombs, we have to show it
            if (numberAdjacentBombs != 0) {
                this.boardState[y][x] = DUG;
                this.updateBombCount(y, x, numberAdjacentBombs);
            }
            // recursively explore
            for (int i = 0; i < yOffsets.length; i ++){
                int adjacentY = y + yOffsets[i];
                int adjacentX = x + xOffsets[i];
                this.recursiveExplore(adjacentY, adjacentX);
                // }
            }
        }
    }

    private void debugDisplay(int y, int x){
        if (this.positionCondition(y, x)){
            System.out.println(this.boardState[y][x]);
        }
    }

    private boolean positionCondition(int y, int x){
        boolean yCondition = (y >= 0 & y < this.sizeY);
        boolean xCondition = (x >= 0 & x < this.sizeX);
        return yCondition & xCondition;
    }

    private int countadjacentBombs(int y, int x) {
        int numberAdjacentBombs = 0;

        for (int i = 0; i < yOffsets.length; i ++){
            int adjacentY = y + yOffsets[i];
            int adjacentX = x + xOffsets[i];

            if  (this.positionCondition(adjacentY, adjacentX)) {
                if (this.boardContent[adjacentY][adjacentX] == BOMB) { 
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

    public String debugContent() {
        StringBuilder sb = new StringBuilder(); 
        // row
        for (int y = 0; y < this.sizeY; y++) {
            // column
            for (int x = 0; x< this.sizeX; x++) {
                sb.append(this.boardContent[y][x]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    
    // observers
    public int getSizeX(){
        return this.sizeX;
    }

    public int getSizeY(){
        return this.sizeY;
    }
}
