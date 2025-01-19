package minesweeper;

public interface Square {

    public Square dig();

    public Square flag();

    public Square deflag();

    @Override
    public String toString();


    
}
