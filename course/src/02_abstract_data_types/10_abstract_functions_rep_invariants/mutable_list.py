from typing import List

class Tweet:
    def __init__(self, author: str, text: str, hashtags: List[str]):
        self.author = author
        self.text = text
        # without defensive copying
        self.hashtags = hashtags

        # with defensive copying
        self.hashtags = hashtags[:]

# Function to create tweets with modified hashtags for each tweet
def tweet_every_hour_today() -> List[Tweet]:
    tweets = []
    hashtags = ["#inspiration", "#motivation", "#positivity"]
    for i in range(24):
        # Modify hashtags in each iteration to simulate a mutable effect
        hashtags.append(f"#hour{i}")  # Adding a unique hashtag each time
        tweets.append(Tweet("rbmllr", f"keep it up! you can do it (Hour {i})", hashtags))
    return tweets

# Creating tweets for every hour today
tweets = tweet_every_hour_today()

# Print hashtags for all tweets to see if each Tweet has an independent copy
for i, tweet in enumerate(tweets):
    print(f"Tweet {i} hashtags: {tweet.hashtags}")

