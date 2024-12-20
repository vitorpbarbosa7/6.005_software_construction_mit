/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.List;
import java.util.Set;
import java.time.Instant;
import java.util.HashSet;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {

        // Single instatenous point on the timeline
        Instant min = tweets.get(0).getTimestamp();
        Instant max = tweets.get(0).getTimestamp();

        for (Tweet tweet: tweets) {
            Instant tweetTimeInstant = tweet.getTimestamp();
            if (tweetTimeInstant.isBefore(min)) {
                min = tweetTimeInstant;
            }
            if (tweetTimeInstant.isAfter(max)) {
               max = tweetTimeInstant; 
            }
        }

        Timespan diffTimespan = new Timespan(min, max);

        return diffTimespan; 
        }       

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) { 
        Set<String> setUsers = new HashSet<>();

        List<String> mentions = new ArrayList<>();
        String text;

        for (Tweet tweet: tweets) {
            text = tweet.getText();

            mentions = extractMentions(text);

            for (String mention : mentions){
                setUsers.add(mention);
            }
        }

        return setUsers;
    }

    public static Set<String> getMentionedHashtags(List<Tweet> tweets) {
        Set<String> setHashtags = new HashSet<>();

        List<String> hashtags = new ArrayList<>();
        String text;

        for (Tweet tweet: tweets) {
            text = tweet.getText();
            hashtags = extractHashtagMentions(text);
            for (String hashtag: hashtags) {
                setHashtags.add(hashtag);
            }
        }

        return setHashtags;
    }

    private static List<String> extractMentions(String text) {
        List<String> mentions = new ArrayList<>();
        
        // Regex explanation:
        // (?<!\\w) ensures there is no alphanumeric character before @ (filters out emails).
        // @ matches the @ symbol.
        // (\\w{1,15}) matches 1 to 15 word characters after the @ (to match Twitter usernames).
        // \\b ensures the username ends with a word boundary (space or punctuation).
        Pattern pattern = Pattern.compile("(?<!\\w)@(\\w{1,15})\\b");
        Matcher matcher = pattern.matcher(text);
    
        while (matcher.find()) {
            mentions.add(matcher.group(1)); // Extract the username without the '@'
        }
    
        return mentions;
    }

    private static List<String> extractHashtagMentions(String text) {
        List<String> hashtags = new ArrayList<>();
        
        // Regex explanation:
        // (?<!\\w) ensures there is no alphanumeric character before #.
        // # matches the # symbol.
        // (\\w+) matches one or more word characters after the # (to match hashtag words).
        // \\b ensures the hashtag ends with a word boundary (space or punctuation).
        Pattern pattern = Pattern.compile("(?<!\\w)#(\\w+)\\b");
        Matcher matcher = pattern.matcher(text);
    
        while (matcher.find()) {
            hashtags.add(matcher.group(1)); // Extract the hashtag without the '#'
        }
    
        return hashtags;
    }

}
