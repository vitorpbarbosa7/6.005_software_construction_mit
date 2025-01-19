package minesweeper;

public class Untouched implements Square{

    private final String squareState = " - ";

    public Untouched(){};


    public Square dig(){
        return new Dug();
    }

    public Square flag(){
        return new Flag();
    }

    public Square deflag(){
        return new Untouched();
    }


    @Override
    public String toString() {
        return squareState;
    }




    
}
