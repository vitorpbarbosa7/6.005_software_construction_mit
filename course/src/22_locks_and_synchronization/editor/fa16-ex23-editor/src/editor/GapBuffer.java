package editor;

/**
 * GapBuffer is a non-threadsafe EditBuffer that is optimized for editing with a 
 * cursor, which tends to make a sequence of inserts and deletes at the same place
 * in the buffer.
 */
public class GapBuffer implements EditBuffer {
    private char[] a;
    private int gapStart;
    private int gapLength;
    // Rep invariant: 
    //   a != null
    //   0 <= gapStart <= a.length
    //   0 <= gapLength <= a.length - gapStart
    // Abstraction function: 
    //   represents the sequence a[0],...,a[gapStart-1],a[gapStart+gapLength],...,a[length-1]
    
    private void checkRep() {
        assert a != null;
        assert 0 <= gapStart;
        assert gapStart <= a.length;
        assert 0 <= gapLength;
        assert gapLength <= a.length - gapStart;
    }
    
    /** Make a GapBuffer. */
    public GapBuffer() {
        a = new char[1];
        gapStart = 0;
        gapLength = 1;
        checkRep();
    }
    
    /** @see EditBuffer#insert */
    public void insert(int pos, String s) {
        int len = s.length();
        moveGap(pos, len);
        s.getChars(0, len, a, gapStart);
        gapStart += len;
        gapLength -= len;
        checkRep();
    }

    /** @see EditBuffer#delete */
    public void delete(int pos, int len) {
        moveGap(pos, 0);
        gapLength += len;
        checkRep();
    }

    /** @see EditBuffer#length */
    public int length() {
        return a.length - gapLength;
    }

    /** @see EditBuffer#toString */
    public String toString() {
        char[] result = new char[a.length-gapLength];
        // copy the part of a before the gap)
        System.arraycopy(a, 0, result, 0, gapStart);
        // copy the part after the gap
        System.arraycopy(a, gapStart+gapLength, result, gapStart, a.length-gapStart-gapLength);
        return String.valueOf(result);
    }
    
    /**
     * Move the gap to a particular position and make sure it's at least a given size.
     * @param newGapStart desired gap start (requires 0 <= newGapStart <= a.length)
     * @param minGapLength desired minimum gap length (requires 0 <= minGapLength).
     * Effects: after this method returns, gapStart == newGapStart and gapLength >= minGapLength,
     * but the text sequence represented by the buffer is unchanged.
     */
    private void moveGap(int newGapStart, int minGapLength) {
        String oldText = null; // used for debugging, only if asserts are turned on
        assert (oldText = toString()) != null;
        
        // determine the target array we'll be copying to, which might be the same
        // as a, or might be a fresh, bigger array
        char[] b; // target array
        int newGapLength; // desired gap length in target array
        if (gapLength >= minGapLength) {
            // gap is already big enough; just use the existing array
            b = a;
            newGapLength = gapLength;
        } else {
            // need to make gap bigger, so need a new array
            int textLength = a.length - gapLength;
            b = new char[Math.max(textLength*2, textLength + minGapLength)];
            newGapLength = b.length - textLength;
        }

        // Now copy text from a to b.
        // Use System.arraycopy to do the copying, first because it's fast (exploiting native block
        // copying where possible), and second because its contract handles the case where we're moving
        // blocks within the same array (i.e. a==b), which is tricky.
        
        // a looks like <prefix_a> <gap_a> <suffix_a>
        // b will look like <prefix_b> <gap_b> <suffix_b>
        // Let's make sure we have names for the endpoints of these ranges,
        // so we'll define gapEnd and newGapEnd:
        //    a: 0...gapStart......gapEnd......a.length
        //    b: 0...newGapStart...newGapEnd...b.length
        int gapEnd = gapStart + gapLength;
        int newGapEnd = newGapStart + newGapLength;
        
        // Two cases: either gap is shifting to the left or to the right.
        //  (if gap stays at the same position, either case will work.)
        if (newGapStart < gapStart) {
            // gap is shifting to the left, a's prefix is longer than b's prefix
            //    a looks like ******___**
            //    b looks like ***________*****
            // copy part of a's prefix, up to b's gap
            System.arraycopy(a, 0, b, 0, newGapStart);
            // copy the rest of a's prefix after b's gap
            System.arraycopy(a, newGapStart, b, newGapEnd, gapStart - newGapStart);
            // copy a's suffix right after it
            System.arraycopy(a, gapEnd, b, newGapEnd + (gapStart - newGapStart), a.length-gapEnd);
        } else {
            // gap is shifting to the right, so a's prefix is shorter than b's prefix
            //    a looks like ***___*****
            //    b looks like ******________**
            // copy all of a's prefix
            System.arraycopy(a, 0, b, 0, gapStart);
            // copy part of a's suffix right after it, up to b's gap
            System.arraycopy(a, gapEnd, b, gapStart, newGapStart - gapStart);
            // copy the rest of a's suffix after b's gap
            System.arraycopy(a, gapEnd + (newGapStart - gapStart), b, newGapEnd, b.length-newGapEnd);
        }
        
        // replace the rep with b
        a = b;
        gapStart = newGapStart;
        gapLength = newGapLength;
        
        // assert the postcondition here to catch bugs early
        assert gapStart == newGapStart;
        assert gapLength >= minGapLength;
        assert toString().equals(oldText); // this is the most important bit
    }
}
