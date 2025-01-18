/** MyStringBuffer is a threadsafe mutable string of characters. */
public class MyStringBuffer {
    // not final
    private String text;
    // Rep invariant: 
    //   none
    // Abstraction function: 
    //   represents the sequence text[0],...,text[text.length()-1]
    // Thread safety argument:
    //   text is an immutable (and hence threadsafe) String, 
    //   so this object is also threadsafe
    
    // observer with not a copy?
    /** @return the string represented by this buffer, with all letters converted to uppercase */
    public String toUpperCase() {
        return text.toUpperCase();
    }
    
    // mutator
    // so probably not thread safe, because a lot of threads can generate a big problem here
    /** @param pos  position to insert text into the buffer.  Requires 0 <= pos <= text.length().
        @param s text to insert
        Mutates this buffer to insert s as a substring at position pos. */        
    public void insert(int pos, String s) {
        text = text.substring(0, pos) + s + text.substring(pos);
    }
    
    // observer with not a copy?
    /** @return the string represented by this buffer */ 
    public String toString() {
        return text;
    }
    
    // mutator
    /** Resets this buffer to the empty string */
    public void clear() {
        text = "";
    }
    
    /** @return the first character of this buffer, or "" if this buffer is empty
     */
    // seems to be safe?
    public String first() {
        if (text.length() > 0) {
            return String.valueOf(text.charAt(0));
        } else {
            return "";
        }
    }

// Why it doesn’t cause a race condition: The clear() method sets text to an empty string, which is a single operation. It doesn’t involve reading or modifying the data inside text. Even though it modifies the state of the object, it doesn't do anything that depends on the current contents of text.

// clear is atomic and does depenp on inside contents?
