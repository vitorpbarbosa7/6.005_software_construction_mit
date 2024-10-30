import requests
import json
import os
import time
from datetime import timedelta

def construct_url(base_url, params):
    return f"{base_url}?{'&'.join([f'{k}={v}' for k, v in params.items()])}"

def should_poll(filename):
    try:
        td = timedelta(seconds=time.time() - os.stat(filename).st_mtime)
        return td > timedelta(hours=1)
    except FileNotFoundError:
        return True

def poll_tweets():
    BEARER_TOKEN = os.getenv("TWITTER_BEARER_TOKEN")
    print(BEARER_TOKEN)
    breakpoint()
    FILENAME = "tweets.json"
    URL = 'https://api.twitter.com/2/tweets/search/recent'
    SEARCH_TERMS = ["olympics", "bitcoin", "mit", "obama"]
    PARAMS = {"tweet.fields": "created_at,author_id,text", "max_results": "100"}
    
    if should_poll(FILENAME):
        statuses = []
        for term in SEARCH_TERMS:
            PARAMS["query"] = term
            statuses += twitter_search(URL, PARAMS, BEARER_TOKEN)
        write_tweets_out(FILENAME, remove_repeats(statuses))

def twitter_search(url, params, bearer_token):
    headers = {"Authorization": f"Bearer {bearer_token}"}
    response = requests.get(construct_url(url, params), headers=headers)
    response.raise_for_status()
    return response.json().get("data", [])

def remove_repeats(tweet_list):
    seen_tweet_ids = set()
    pruned_tweets = []
    for tweet in tweet_list:
        if tweet["id"] not in seen_tweet_ids:
            seen_tweet_ids.add(tweet["id"])
            pruned_tweets.append(tweet)
    return pruned_tweets

def write_tweets_out(filename, data):
    with open(filename, 'w') as f:
        json.dump(data, f, indent=4)

def main():
    poll_tweets()

if __name__ == "__main__":
    main()

