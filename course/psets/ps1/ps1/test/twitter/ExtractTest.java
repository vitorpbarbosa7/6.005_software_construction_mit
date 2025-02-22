/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */

     // ids
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T12:00:00Z");
   
    // tweets objects
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #cisc", d2);
    private static final Tweet tweet3 = new Tweet(3, "charles", "rivest talk was amazing! #risc", d3);
    private static final Tweet tweetoneuser = new Tweet(4, "charles", "rivest talk with my girlfriend @natalieportman* was amazing!", d4);
    private static final Tweet tweettwousers = new Tweet(5, "charles", "two users: @yamal, @lewa&", d4);
    private static final Tweet tweetsingleuser = new Tweet(6, "charles", "two users: @yamal!", d4);
    private static final Tweet tweetyamal = new Tweet(7, "charles", "two users: @yamal+", d4);
    private static final Tweet tweetyamal2 = new Tweet(8, "charles", "two users: @yamal% @yamal@", d4);

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
   
    /*
     * 01: two cases, one higher than the other
     * 02: three cases, all with different values
     * 03: one single case
     */
    @Test
    public void testGetTimespanTwoTweets() {

        // 00:
        List<Tweet> tweetListEmpty = new ArrayList<>();
        assertTrue("expected empty set", tweetListEmpty.isEmpty());

        List<Tweet> tweetList1 = Arrays.asList(tweet2, tweet1);
        Timespan timespan = Extract.getTimespan(tweetList1);    
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());

        List<Tweet> tweetList2 = Arrays.asList(tweet3, tweet2, tweet1);
        Timespan timespan2 = Extract.getTimespan(tweetList2);
        assertEquals("expec start", d1, timespan2.getStart());
        assertEquals("expect end", d3, timespan2.getEnd());

        List<Tweet> tweetList3 = Arrays.asList(tweet1);
        Timespan timespan3 = Extract.getTimespan(tweetList3);
        assertEquals("single", d1, timespan3.getStart());
        assertEquals("single", d1, timespan3.getEnd());

    }
    /*
     * 01: emptyuserset
     * 02: singleuser
     * 03: two different users
     * 04: two users two tweets
     * 05: two same users
     */
    @Test
    public void testGetMentionedUsersNoMention() {
        // 01
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        assertTrue("expected empty set", mentionedUsers.isEmpty());

        // 02
        Set<String> tweet4Users = new HashSet<>();
        String tweet4User = "natalieportman";
        tweet4Users.add(tweet4User);
        Set<String> mentionedUsers4 = Extract.getMentionedUsers(Arrays.asList(tweetoneuser));
        assertEquals("single user", tweet4Users, mentionedUsers4);

        // 03 
        Set<String> tweetSetTwoUsers = new HashSet<>();
        tweetSetTwoUsers.add("yamal");
        tweetSetTwoUsers.add("lewa");
        Set<String> mentionedTwoUsers = Extract.getMentionedUsers(Arrays.asList(tweettwousers));
        assertEquals("two users", tweetSetTwoUsers, mentionedTwoUsers);

        // 04
        Set<String> tweetSetTwoUsersTwoTweets = new HashSet<>();
        List<String> arrayList = new ArrayList<>(List.of("yamal", "lewa")); 
        tweetSetTwoUsersTwoTweets.addAll(arrayList);
        Set<String> mentionedTwoUsersTwoTweets = Extract.getMentionedUsers(Arrays.asList(tweettwousers, tweetsingleuser));
        assertEquals("two users two tweets", tweetSetTwoUsersTwoTweets, mentionedTwoUsersTwoTweets);

        // 05
        Set<String> tweetSetSingleUserThreeTweets = new HashSet<>();
        List<String> arrayListThreeTweets = new ArrayList<>(List.of("yamal")); 
        tweetSetSingleUserThreeTweets.addAll(arrayListThreeTweets);
        Set<String> mentionedSingleUserThreeTweets = Extract.getMentionedUsers(Arrays.asList( tweetsingleuser, tweetyamal, tweetyamal2));
        assertEquals("two users two tweets", tweetSetSingleUserThreeTweets, mentionedSingleUserThreeTweets);

        
    }

    @Test
    public void testGetMentionedHashtags() {
        // 01
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        assertTrue("expected empty set", mentionedUsers.isEmpty());

        // 02
        Set<String> expectedHashtags2 = new HashSet<>();
        String tweet4risc = "risc";
        String tweet4cisc = "cisc";
        expectedHashtags2.add(tweet4risc);
        expectedHashtags2.add(tweet4cisc);
        Set<String> mentionedHashtags2 = Extract.getMentionedHashtags(Arrays.asList(tweet2, tweet3));
        assertEquals("2 Hashtags", expectedHashtags2, mentionedHashtags2);
        
    }





    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
