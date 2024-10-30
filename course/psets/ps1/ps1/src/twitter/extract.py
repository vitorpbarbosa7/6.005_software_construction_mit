import tweepy
import json
import os

# Load API keys from environment variables
CONSUMER_KEY = os.getenv("TWITTER_CONSUMER_KEY")
CONSUMER_SECRET = os.getenv("TWITTER_CONSUMER_SECRET")
ACCESS_TOKEN = os.getenv("TWITTER_ACCESS_TOKEN")
ACCESS_TOKEN_SECRET = os.getenv("TWITTER_ACCESS_TOKEN_SECRET")
BEARER_TOKEN = os.getenv("TWITTER_BEARER_TOKEN")

# Setup Tweepy client for API v2
client = tweepy.Client(bearer_token=BEARER_TOKEN)

def fetch_tweets(username="vitorpbarbosa7", tweet_count=5):
    try:
        # Fetch user ID first (API v2 requires this for user tweets)
        user = client.get_user(username=username)
        if not user:
            print(f"User {username} not found.")
            return
        
        user_id = user.data.id

        # Fetch tweets using API v2
        tweets = client.get_users_tweets(id=user_id, max_results=tweet_count, tweet_fields=["created_at", "text"])

        # Prepare tweets for saving in JSON format
        tweets_data = []
        for tweet in tweets.data:
            tweet_info = {
                "id": tweet.id,
                "author": username,
                "text": tweet.text,
                "timestamp": tweet.created_at.isoformat()
            }
            tweets_data.append(tweet_info)

        # Save tweets to tweets.json
        with open("tweets.json", "w") as file:
            json.dump(tweets_data, file, indent=4)
        
        print(f"Successfully saved {len(tweets_data)} tweets to tweets.json")
    
    except tweepy.errors.Forbidden as e:
        print("Access error:", e)
    except Exception as e:
        print("An unexpected error occurred:", e)

# Fetch and save tweets
if __name__ == "__main__":
    fetch_tweets(username="vitorpbarbosa7", tweet_count=5)

