/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

public class Main {

    /**
     * Main method of the program. Reads a sample of tweets from a local file and prints some
     * facts about it.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            assert false;
            throw new Error("Always run main and tests with assertions enabled");
        } catch (AssertionError ae) { }
        
        final List<Tweet> tweets;
        try {
            tweets = TweetReader.readTweetsFromFile("src/twitter/tweets.json");  // Read from a local file
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        
        // display some characteristics about the tweets
        System.err.println("fetched " + tweets.size() + " tweets");
        
        final Timespan span = Extract.getTimespan(tweets);
        System.err.println("ranging from " + span.getStart() + " to " + span.getEnd());
        
        final Set<String> mentionedUsers = Extract.getMentionedUsers(tweets);
        System.err.println("covers " + mentionedUsers.size() + " Twitter users");
        
        // infer the follows graph
        final Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        System.err.println("follows graph has " + followsGraph.size() + " nodes");
        
        // print the top-N influencers
        final int count = 10;
        final List<String> influencers = SocialNetwork.influencers(followsGraph);
        for (String username : influencers.subList(0, Math.min(count, influencers.size()))) {
            System.out.println(username);
        }
    }
    
}

/* Class to read tweets from a local JSON file. */
class TweetReader {
    
    /**
     * Get a list of tweets from a local file.
     * 
     * @param filename name of the file to retrieve tweets from
     * @return a list of tweets retrieved from the file.
     * @throws IOException if the file is not found or can't be read.
     */
    public static List<Tweet> readTweetsFromFile(String filename) throws IOException {
        try (Reader reader = new FileReader(filename)) {
            return readTweets(reader);
        }
    }
    
    /*
     * Read a list of tweets from a stream.
     * 
     * @return a list of tweets parsed out of the stream.
     */
    private static List<Tweet> readTweets(Reader reader) {
        JsonReader jsonReader = Json.createReader(reader);
        JsonArray array = jsonReader.readArray();
        ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
        for (int i = 0; i < array.size(); i++) {
            Map<String, Object> map = constructTweetMap(array.get(i), null);
            tweetList.add(createTweetFromMap(map));
        }
        return tweetList;
    }
    
    /*
     * Crawl recursively through the JSON tree representing a single tweet.
     * 
     * @return a map that maps key paths (like "id" and "user.screen_name") to values.
     */
    private static Map<String, Object> constructTweetMap(JsonValue tree, String key) {
        Map<String, Object> tweetMap = new HashMap<String, Object>();
        switch (tree.getValueType()) {
        case OBJECT:
            JsonObject object = (JsonObject) tree;
            Map<String, Object> subMap = new HashMap<String, Object>();
            for (String name : object.keySet()) {
                subMap.putAll(constructTweetMap(object.get(name), name));
            }
            if (key == null) {
                tweetMap.putAll(subMap);
            } else {
                tweetMap.put(key, subMap);
            }
            break;
        case ARRAY:
            JsonArray array = (JsonArray) tree;
            for (JsonValue val : array)
                tweetMap.putAll(constructTweetMap(val, null));
            break;
        case STRING:
            JsonString st = (JsonString) tree;
            tweetMap.put(key, st.getString());
            break;
        case NUMBER:
            JsonNumber num = (JsonNumber) tree;
            tweetMap.put(key, num.toString());
            break;
        case TRUE:
        case FALSE:
        case NULL:
            tweetMap.put(key, tree.getValueType().toString());
            break;
        default:
            throw new JsonException("Unexpected value type " + tree.getValueType());
        }
        
        return tweetMap;
    }
    
    /*
     * Construct a Tweet from a map of (key,value) pairs parsed from JSON.
     */

        /*
         * Construct a Tweet from a map of (key, value) pairs parsed from JSON.
         */
        private static Tweet createTweetFromMap(Map<String, Object> tweetMap) {
            // Check each required field and throw an exception if any are missing
            if (tweetMap.get("id") == null) {
                throw new IllegalArgumentException("Missing required field: id");
            }
            if (tweetMap.get("text") == null) {
                throw new IllegalArgumentException("Missing required field: text");
            }
            if (tweetMap.get("created_at") == null) {
                throw new IllegalArgumentException("Missing required field: created_at");
            }
            if (tweetMap.get("user.screen_name") == null) {
                throw new IllegalArgumentException("Missing required field: user.screen_name");
            }
        
            // If all fields are present, create and return the Tweet object
            Long id = Long.valueOf(tweetMap.get("id").toString());
            String screenName = tweetMap.get("user.screen_name").toString();
            String text = tweetMap.get("text").toString();
            ZonedDateTime timestamp = ZonedDateTime.parse(
                tweetMap.get("created_at").toString(),
                DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.US)
            );
            
            return new Tweet(id, screenName, text, timestamp.toInstant());
        }
}
