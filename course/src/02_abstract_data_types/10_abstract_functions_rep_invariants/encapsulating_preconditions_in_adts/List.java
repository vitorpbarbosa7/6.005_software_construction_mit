/**
 * Find tweets written by a particular user
 *
 * @param tweets a list of tweets with distinct timestamps, not mofidified by this method.
 * @param username Twitter username (a nonempty sequence of letters, digits, and underscore)
 * @return all and only the tweets in the list whose author is username,
 *          in the same order as in the input list. 
 */
public static List<Tweet> writtenBy(List<Tweet> tweets, String username) { ... }


// This would be better replace by 
public static            writtenBy(TweetList tweets, Username username) { ... }
