/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser;

public class UnableToParseException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -4655011312226849339L;    

    public UnableToParseException(String message) {
        super(message);
    }
}
