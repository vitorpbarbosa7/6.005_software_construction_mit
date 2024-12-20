/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2017-02-17T11:00:00Z");
    private static final Instant d4 = Instant.parse("2017-02-17T12:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk @bbitdiddle about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 @alyssa minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "alyssa", " tweet to my  @ronald chap", d3);
    private static final Tweet tweet4 = new Tweet(4, "ronald", "why life @alyssa why", d4);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        // 01 - two tweets, return only single one for alyssa
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
        
        // 02 - three tweets, return two tweets by alyssa
        List<Tweet> writtenByAlyssaTwo = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet3), "alyssa");
        
        assertEquals("expected two", 2, writtenByAlyssaTwo.size());
        assertTrue("expected list to contain tweet", writtenByAlyssaTwo.contains(tweet1));
        assertTrue("expected list to contain tweet", writtenByAlyssaTwo.contains(tweet3));
        assertFalse("should be on the list", writtenByAlyssaTwo.contains(tweet2));
        // the same:
        assertEquals("expected list of tweets by alyssa", List.of(tweet1, tweet3), writtenByAlyssaTwo);

        //03 Invert the order
        List<Tweet> writtenByAlyssaThree = Filter.writtenBy(Arrays.asList(tweet3, tweet2, tweet1), "alyssa");
        assertEquals("expected list of tweets by alyssa", List.of(tweet3, tweet1), writtenByAlyssaThree);
        assertEquals("expect the same order", 0, writtenByAlyssaThree.indexOf(tweet3));

    }
    
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        // given the timespam, we look at our tweets, and see if they should match or not
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
        
        //02 - give tweet that is not in range, should return empty
        List<Tweet> outTimespanOne = Filter.inTimespan(Arrays.asList(tweet3), new Timespan(testStart, testEnd));
        assertTrue("Expect list to be empty", outTimespanOne.isEmpty());

        List<Tweet> outTimespanThree = Filter.inTimespan(Arrays.asList(tweet4, tweet3), new Timespan(testStart, testEnd));
        assertTrue("Expect list to be empty", outTimespanThree.isEmpty());

        // 03 - Return the ones it should, and not others
        List<Tweet> inTimespanFilter = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4), new Timespan(testStart, testEnd));
        assertTrue("Expect list to have tweet 1", inTimespanFilter.contains(tweet1));
        assertTrue("Expect list to have tweet 2", inTimespanFilter.contains(tweet2));
        assertFalse("Expect list not to have tweet 3", inTimespanFilter.contains(tweet3));
        assertFalse("Expect list not to have tweet 4", inTimespanFilter.contains(tweet4));
        assertEquals("Expect length 2", 2, inTimespanFilter.size());
        assertEquals("expected same order", 0, inTimespanFilter.indexOf(tweet1));
    }
    
    @Test
    public void testContaining() {
        // -01 return the two tweets that exist
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk"));
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, containing.indexOf(tweet1));

        // 02 return empty
        List<Tweet> notContaining = Filter.containing(Arrays.asList(tweet3), Arrays.asList("talk"));
        assertTrue("Expect to be empty", notContaining.isEmpty());

        // 03 With full list, return only with the talk
        List<Tweet> twoTweets = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3, tweet4),Arrays.asList( "talk"));
        assertTrue("Expect list to have tweet 1", twoTweets.contains(tweet1));
        assertTrue("Expect list to have tweet 2", twoTweets.contains(tweet2));
        assertFalse("Expect list not to have tweet 3", twoTweets.contains(tweet3));
        assertFalse("Expect list not to have tweet 4", twoTweets.contains(tweet4));

        // 04 two different words
        List<Tweet> twoWords = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3, tweet4), Arrays.asList("chap", "life"));
        assertTrue("Expect list to have tweet 3", twoWords.contains(tweet3));
        assertTrue("Expect list to have tweet 4", twoWords.contains(tweet4));
        assertFalse("Expect list not to have tweet 1", twoWords.contains(tweet1));
        assertFalse("Expect list not to have tweet 2", twoWords.contains(tweet2));
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
