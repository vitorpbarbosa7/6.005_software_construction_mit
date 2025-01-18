from datetime import datetime, timedelta
from typing import List

class Tweet:
    def __init__(self, author: str, text: str, timestamp: datetime):
        self.author = author
        self.text = text
        self.timestamp = timestamp  # No defensive copy

# Function to create tweets every hour, similar to tweetEveryHourToday
def tweet_every_hour_today() -> List[Tweet]:
    tweets = []
    date = datetime.now().replace(minute=0, second=0, microsecond=0)  # Start from the top of the current hour
    for i in range(24):
        date = date.replace(hour=i)  # Set the hour
        tweets.append(Tweet("rbmllr", "keep it up! you can do it", date))
    return tweets

# Creating tweets for every hour today
tweets = tweet_every_hour_today()

# Modifying one tweet's timestamp (e.g., the first one) will affect all other tweets
tweets[0].timestamp = tweets[0].timestamp + timedelta(hours=1)

# Print timestamps for all tweets to see the unintended effect
for i, tweet in enumerate(tweets):
    print(f"Tweet {i} timestamp: {tweet.timestamp}")

