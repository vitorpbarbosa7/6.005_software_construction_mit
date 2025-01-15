package editor;

/**
 * An EditBuffer represents a threadsafe mutable string of characters in a text editor.
 */
public interface EditBuffer {

    /**
     * Modifies this by inserting a string.
     * @param i position to insert (requires 0 <= pos <= current buffer length)
     * @param s string to insert
     */
    public void insert(int pos, String s);
    
    /**
     * Modifies this by deleting a substring
     * @param pos start of substring to delete (requires 0 <= pos <= current buffer length)
     * @param len length of substring to delete (requires 0 <= len <= current buffer length - pos)
     */
    public void delete(int pos, int len);
    
    /**
     * @return length of text sequence in this edit buffer
     */
    public int length();
    
    /**
     * @return content of this edit buffer
     */
    @Override public String toString();
}
