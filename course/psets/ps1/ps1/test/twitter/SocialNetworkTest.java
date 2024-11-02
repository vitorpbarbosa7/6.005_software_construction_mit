/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    // Additional tweet timestamps
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T13:00:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T14:00:00Z");
    private static final Instant d7 = Instant.parse("2016-02-17T15:00:00Z");
    private static final Instant d8 = Instant.parse("2024-02-17T15:00:00Z");

   
    // tweets objects
    private static final Tweet tweet5 = new Tweet(5, "shannon", "Learning a lot but not mentioning anyone.", d5); // No mentions
    private static final Tweet tweet1 = new Tweet(1, "knuth", "I follow @turing", d1);
    private static final Tweet tweet4 = new Tweet(4, "turing", "Thanks to @knuth for the inspiration!", d4);
    private static final Tweet tweet2 = new Tweet(2, "lovelace", "I follow @gracehopper", d2);
    private static final Tweet tweet7 = new Tweet(7, "lovelace", "@turing and @shannon, let’s meet to discuss ideas!", d7);
    private static final Tweet tweet3 = new Tweet(3, "hopper", "Great work by @lovelace and @knuth!", d3);
    private static final Tweet tweet8 = new Tweet(8, "hopper", "Great work by @demaine and @lovelace!", d8);
    private static final Tweet tweet9 = new Tweet(8, "musk", "i'm crazy", d8);
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }

    @Test
    public void testGuessFollowsGraphCase01() {

        // return from function
        List<Tweet> tweetList01 = new ArrayList<>(List.of(tweet1, tweet2));
        Map<String, Set<String>> followsGraph01 = SocialNetwork.guessFollowsGraph(tweetList01);

        // created desired result
        Map<String, Set<String>> realHashMap01 = new HashMap<>(); 
        Set<String> HashSetKnuth = new HashSet<>();
        HashSetKnuth.add("turing"); 
        realHashMap01.put("knuth", HashSetKnuth);

        // compare
        assertEquals("espect the same hash set values", realHashMap01.get("knuth"), followsGraph01.get("knuth"));

    }

    @Test
    public void testGuessFollowsGraphMultipleMentionsByDifferentAuthors() {
        // List of tweets where different users mention various other users
        List<Tweet> tweetList = Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet7, tweet8, tweet9);

        // Expected graph where each user follows the mentioned users
        Map<String, Set<String>> expectedGraph = new HashMap<>();
        expectedGraph.put("hopper", new HashSet<>(Arrays.asList("lovelace", "knuth", "demaine")));
        expectedGraph.put("turing", new HashSet<>(Arrays.asList("knuth")));
        expectedGraph.put("shannon", new HashSet<>());
        expectedGraph.put("lovelace", new HashSet<>(Arrays.asList("gracehopper", "turing", "shannon")));
        expectedGraph.put("knuth", new HashSet<>(Arrays.asList("turing")));
        expectedGraph.put("demaine", new HashSet<>());
        expectedGraph.put("gracehopper", new HashSet<>());
        expectedGraph.put("musk", new HashSet<>());

        // Actual graph generated by the function
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweetList);

        assertEquals("expected correct follow relationships for multiple mentions", expectedGraph, followsGraph);
    }

    @Test
    public void testGuessFollowsGraphNoMentions() {
        // List of tweets with no mentions
        List<Tweet> tweetListNoMentions = Arrays.asList(tweet5);

        // Expected graph is empty since no one mentions anyone
        Map<String, Set<String>> expectedGraph = new HashMap<>();

        // Actual graph generated by the function
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweetListNoMentions);

        assertEquals("expected empty graph for tweets with no mentions", expectedGraph, followsGraph);
    }

    @Test
    public void testGuessFollowsGraphMultipleMentionsBySameAuthor() {
        // List of tweets where the same user mentions multiple users in one tweet
        List<Tweet> tweetListSameAuthorMultipleMentions = Arrays.asList(tweet7);

        // Expected graph where the author "lovelace" follows multiple users
        Map<String, Set<String>> expectedGraph = new HashMap<>();
        expectedGraph.put("lovelace", new HashSet<>(Arrays.asList("turing", "shannon")));

        // Actual graph generated by the function
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweetListSameAuthorMultipleMentions);

        assertEquals("expected correct follow relationships for multiple mentions by the same author", expectedGraph, followsGraph);
    }

    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}