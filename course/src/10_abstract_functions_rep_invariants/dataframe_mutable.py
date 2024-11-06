from typing import List
import pandas as pd

class Tweet:
    def __init__(self, author: str, text: str, hashtags: pd.DataFrame):
        self.author = author
        self.text = text
        # No defensive copying - shared reference
        self.hashtags = hashtags

# Function to create tweets with modified hashtags for each tweet
def tweet_every_hour_today() -> List[Tweet]:
    tweets = []
    # Initialize DataFrame with initial hashtags
    hashtags = pd.DataFrame({"Hashtags": ["#inspiration", "#motivation", "#positivity"]})
    
    for i in range(5):  # Limiting to 5 iterations
        # Directly modify the original DataFrame by adding a new row
        hashtags.loc[len(hashtags)] = f"#hour{i}"
        
        # Create a new Tweet without defensive copy of hashtags
        tweets.append(Tweet("rbmllr", f"keep it up! you can do it (Hour {i})", hashtags))
    
    return tweets

# Creating tweets for every hour today
tweets = tweet_every_hour_today()

# Print hashtags for all tweets to see the unintended effect of shared references
for i, tweet in enumerate(tweets):
    print(f"Tweet {i} hashtags:\n{tweet.hashtags}\n")

