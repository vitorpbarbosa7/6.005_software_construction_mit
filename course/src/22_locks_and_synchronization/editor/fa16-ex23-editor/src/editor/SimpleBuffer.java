package editor;

/**
 * SimpleBuffer is a threadsafe EditBuffer with a simple rep.
 */
public class SimpleBuffer implements EditBuffer {

    private String text;
    // Rep invariant: 
    //   text, lock != null
    // Abstraction function: 
    //   represents the sequence text[0],...,text[text.length()-1]
    // Thread safety argument:
    //    all accesses to text happen within SimpleBuffer methods
    //    which are all guarded by SImpleBuffer's lock 
    // Concurrency argument:
    //   TODO

    private void checkRep() {
        assert text != null;
    }

    /** Make a SimpleBuffer. */
    public SimpleBuffer() {
        text = "";
        checkRep();
    }

    /** @see EditBuffer#insert */
    public void insert(int pos, String s) {
        // insert in the this middle position 
        text = text.substring(0, pos) + s + text.substring(pos);
        checkRep();
    }

    /** @see EditBuffer#delete */
    // delete in this middle position 
    // keep 0 to the position and position to the length, eliminating position
    public void delete(int pos, int len) {
        text = text.substring(0, pos) + text.substring(pos+len);
        checkRep();
    }

    /** @see EditBuffer#length */
    public int length() {
        return text.length();
    }

    /** @see EditBuffer#toString */
    public String toString() {
        return text;
    }
}
