package editor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class contains test cases for the EditBuffer interface.
 * It can't be run as a JUnit test suite by itself; it needs to be subclassed to
 * override the factory method make().
 */
public abstract class EditBufferTest {
    /** @return an empty EditBuffer */
    public abstract EditBuffer make();

    // make sure assertions are turned on, so that checkRep() runs
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    // Testing strategy:
    //
    // Partition char sequences into empty, one-char, many-chars
    // Partition positions into start, middle, end
    // Partition deletion lengths into 0, 1, many
    // Partition deletion-ends into start of buffer, middle of buffer, end of buffer
    //
    // Test insert with each partition for buf.insert(pos, s)
    // Test delete with each partition for buf.delete(pos, len)
    // Test toString and length by using them for assertions
    
    @Test
    public void testInsert() {
        EditBuffer buf = make();
        assertEquals("", buf.toString());
        assertEquals(0, buf.length());
        
        buf.insert(0, "a"); // empty.insert(start, one-char)
        assertEquals("a", buf.toString());
        assertEquals(1, buf.length());

        buf.insert(1, "bcd"); // one-char.insert(end, many-chars)
        assertEquals("abcd", buf.toString());
        assertEquals(4, buf.length());

        buf.insert(2, ""); // many-chars.insert(middle, empty)
        assertEquals("abcd", buf.toString());
        assertEquals(4, buf.length());
    }

    @Test
    public void testDelete() {
        EditBuffer buf = make();

        buf.insert(0, "abcdefghi");
        assertEquals("abcdefghi", buf.toString());
        assertEquals(9, buf.length());
        
        buf.delete(0, 0); // many.delete(start, 0, start of buffer)
        assertEquals("abcdefghi", buf.toString());
        assertEquals(9, buf.length());

        buf.delete(0, 1); // many.delete(start, 1, middle of buffer)
        assertEquals("bcdefghi", buf.toString());
        assertEquals(8, buf.length());
        
        buf.delete(7, 0); // many.delete(end, 0, end of buffer)
        assertEquals("bcdefghi", buf.toString());
        assertEquals(8, buf.length());

        buf.delete(2, 3); // many.delete(middle, many, middle of buffer)
        assertEquals("bcghi", buf.toString());
        assertEquals(5, buf.length());
    }
    
    
    
//    private static final int N = 1000;
//
//    @Test
//    public void testManyInserts() {        
//        EditBuffer buf = make();
//        doManyInserts(buf, "a", N);
//        doManyInserts(buf, "b", N);
//        assertEquals(N, count(buf.toString(), "a"));
//        assertEquals(N, count(buf.toString(), "b"));        
//    }
//
//    /* insert s n times at random locations in buf. */
//    private static void doManyInserts(EditBuffer buf, String s, int n) {
//        for (int i = 0; i < n; ++i) {
//            int pos = (int) (Math.random() * buf.length());
//            buf.insert(pos, s);
//        }
//    }
//    
//    /* @return number of times needle occurs in haystack (including overlapping matches). */
//    private static int count(String haystack, String needle) {
//        int n = 0;
//        for (int i = haystack.indexOf(needle); i != -1; i = haystack.indexOf(needle, i+1)) {
//            ++n;
//        }
//        return n;
//    }
    
    
    
//    @Test
//    public void testManyInsertsMultithreaded() {        
//        EditBuffer buf = make();
//        Set<Thread> threads = new HashSet<Thread>();
//        threads.add(startInserterThread(buf, "a", N));
//        threads.add(startInserterThread(buf, "b", N));
//        waitForAll(threads);
//        assertEquals(N, count(buf.toString(), "a"));
//        assertEquals(N, count(buf.toString(), "b"));        
//    }
//    
//    /* @return a started thread making n randomly-positioned inserts of s into buf. */
//    private static Thread startInserterThread(final EditBuffer buf, final String s, final int n) {
//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//                Thread.yield();
//                doManyInserts(buf, s, n);
//            }
//        });
//        thread.start();
//        return thread;        
//    }
//    
//    /* wait for all threads in set to complete. */
//    private static void waitForAll(Set<Thread> threads) {
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new AssertionError("don't interrupt my testing");
//            }
//        }                
//    }
    
    
    
//    @Test
//    public void testFindReplace() {        
//        EditBuffer buf = make();
//        doManyInserts(buf, "a", N);
//        doManyInserts(buf, "b", N);
//        assertEquals(N, count(buf.toString(), "a"));
//        assertEquals(N, count(buf.toString(), "b"));        
//
//        while (Editor.findReplace(buf, "a", "c") || Editor.findReplace(buf, "b", "d")) {             
//        }
//        assertEquals(0, count(buf.toString(), "a"));
//        assertEquals(0, count(buf.toString(), "b"));        
//        assertEquals(N, count(buf.toString(), "c"));
//        assertEquals(N, count(buf.toString(), "d"));        
//    }
    
    
    
//    @Test
//    public void testFindReplaceMultithreaded() {
//        EditBuffer buf = make();
//        
//        doManyInserts(buf, "a", N);
//        doManyInserts(buf, "b", N);
//        assertEquals(N, count(buf.toString(), "a"));
//        assertEquals(N, count(buf.toString(), "b"));        
//
//        Set<Thread> threads = new HashSet<Thread>();
//        threads.add(startReplacerThread(buf, "a", "c"));
//        threads.add(startReplacerThread(buf, "b", "d"));
//        waitForAll(threads);
//        assertEquals(0, count(buf.toString(), "a"));
//        assertEquals(0, count(buf.toString(), "b"));
//        assertEquals(N, count(buf.toString(), "c"));
//        assertEquals(N, count(buf.toString(), "d"));
//    }
//    
//    /* @return a started thread that replaces all occurrences of s with t in buf. */
//    private static Thread startReplacerThread(final EditBuffer buf, final String s, final String t) {
//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//                Thread.yield();
//                while (Editor.findReplace(buf, s, t)) {
//                }
//            }
//        });
//        thread.start();
//        return thread;        
//    }
}
