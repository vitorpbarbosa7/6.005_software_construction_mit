/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        Map<String, Set<String>> socialNetwork = new HashMap<>();

        // 01 -> go over the list of tweets and extract usernames
        //02 -> also extract mentions, and add to the list
        Set<String> setUsers = new HashSet<>();

        for (Tweet tweet: tweets){
            setUsers.add(tweet.getAuthor());

            Set<String> mentionedUsers = Extract.getMentionedUsers(tweets);
            for (String mentionedUser : mentionedUsers){
                setUsers.add(mentionedUser);
            }
        }

        // 03 now get what each one follows to populate the HashMap
        for (String user: setUsers){
            // what are the tweets from this user
            List<Tweet> userWrittenTweets = new ArrayList<>();
            userWrittenTweets = Filter.writtenBy(tweets, user);

            System.out.println("tweets from user");
            System.out.println(user);
            System.out.println(userWrittenTweets);



            // on those tweets, let's look for mentions of users in the list of users
            Set<String> followingUsers = new HashSet<>(); 
            Set<String> mentionedUsers = Extract.getMentionedUsers(userWrittenTweets);

            for (String mentionedUser: mentionedUsers) {
                followingUsers.add(mentionedUser);
            }

            socialNetwork.put(user, followingUsers);
        }
        System.out.println(socialNetwork);
            
        return socialNetwork;

    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {

        List<String> mostFollowers = new ArrayList<>();

        Map<String, Integer> countMap = new HashMap<>();

        for (Set<String> setFollowed: followsGraph.values()){
            for (String followed: setFollowed) {
                countMap.put(followed, countMap.getOrDefault(followed, 0) + 1);
            }
        }
        System.out.println("CountMap");
        System.out.println(countMap);

        mostFollowers = getKeysSortedByValueDescending(countMap);

        System.out.println(mostFollowers);
        return mostFollowers;
    } 


        
    private static List<String> getKeysSortedByValueDescending(Map<String, Integer> map) {
        // Create a Data Structure for the entries
        //Map.Entry<K,V>
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        // after we sort by comparing
        // Lambda expression or comparator to know how the comparison will be done, for sorting 
        // is this at least O(nlogn) or worse?
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Extract the sorted keys into a list
        List<String> sortedKeys = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedKeys.add(entry.getKey());
        }

        return sortedKeys;
    }
    }

