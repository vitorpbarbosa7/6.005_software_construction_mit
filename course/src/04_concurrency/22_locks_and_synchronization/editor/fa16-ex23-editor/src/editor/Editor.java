package editor;

public class Editor {
    
    // ...
    
    /**
     * Modifies buf by replacing the first occurrence of s with t.
     * If s not found in buf, then has no effect.
     * @returns true if and only if a replacement was made
     */
    public static boolean findReplace(EditBuffer buf, String s, String t) {
        int i = buf.toString().indexOf(s);
        if (i == -1) {
            return false;
        }
        buf.delete(i, s.length());
        buf.insert(i, t);
        return true;
    }
    
    // ...
}
