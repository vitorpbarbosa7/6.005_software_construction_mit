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

    private final String untouched = " - ";
    private final String flag = " F ";
    private final String dug = "  ";
    private final String bomb = "b";
    private final String empty =  "n";

    private final Set<Position> bombPositions = new HashSet<>();

    public Board(int sizeX,int sizeY) {
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.boardContent = new String[sizeY][sizeX];
        this.boardState = new String[sizeY][sizeX];

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
    
                // Check if there's a bomb at (x, y)
                if (bombPositions.contains(new Position(x, y))) {
                    this.boardContent[y][x] = bomb; // Place bomb
                } else {
                    this.boardContent[y][x] = empty; // Place empty square
                }
            }
        }
    }

    public void dig(int x, int y){
        if (x < 0 || y < 0) {

        } else if (y > this.sizeY || x > this.sizeX ){
            
        } else if (this.boardState[y][x] != untouched)
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        // row
        for (int y = 0; y < this.sizeY; y++) {
            // column
            for (int x = 0; x< this.sizeX; x++) {
                sb.append(this.boardState[y][x]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    } 
}
