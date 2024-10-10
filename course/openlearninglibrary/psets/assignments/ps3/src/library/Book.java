package library;

import java.util.List;

/**
 * Book is an immutable type representing an edition of a book -- not the physical object, 
 * but the combination of words and pictures that make up a book.  Each book is uniquely
 * identified by its title, author list, and publication year.  Alphabetic case and author 
 * order are significant, so a book written by "Fred" is different than a book written by "FRED".
 */
public class Book {

    // TODO: rep
    
    // TODO: rep invariant
    // TODO: abstraction function
    // TODO: safety from rep exposure argument
    
    /**
     * Make a Book.
     * @param title Title of the book. Must contain at least one non-space character.
     * @param authors Names of the authors of the book.  Must have at least one name, and each name must contain 
     * at least one non-space character.
     * @param year Year when this edition was published in the conventional (Common Era) calendar.  Must be nonnegative. 
     */
    public Book(String title, List<String> authors, int year) {
        throw new RuntimeException("not implemented yet");
    }
    
    // assert the rep invariant
    private void checkRep() {
        throw new RuntimeException("not implemented yet");
    }
    
    /**
     * @return the title of this book
     */
    public String getTitle() {
        throw new RuntimeException("not implemented yet");
    }
    
    /**
     * @return the authors of this book
     */
    public List<String> getAuthors() {
        throw new RuntimeException("not implemented yet");
    }

    /**
     * @return the year that this book was published
     */
    public int getYear() {
        throw new RuntimeException("not implemented yet");
    }

    /**
     * @return human-readable representation of this book that includes its title,
     *    authors, and publication year
     */
    public String toString() {
        throw new RuntimeException("not implemented yet");
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
    // @Override
    // public boolean equals(Object that) {
    //     throw new RuntimeException("not implemented yet");
    // }
    // 
    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
