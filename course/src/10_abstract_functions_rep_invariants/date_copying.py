from datetime import datetime, timedelta

class Event:
    def __init__(self, name, timestamp):
        # We create a copy of timestamp to avoid exposing the original reference
        self.name = name
        self.timestamp = datetime(timestamp.year, timestamp.month, timestamp.day, timestamp.hour, timestamp.minute, timestamp.second)

    def get_timestamp(self):
        # Return a copy of the timestamp to protect the internal state
        return datetime(self.timestamp.year, self.timestamp.month, self.timestamp.day, self.timestamp.hour, self.timestamp.minute, self.timestamp.second)

# Example usage
event = Event("Meeting", datetime(2023, 1, 1, 10, 0))
timestamp = event.get_timestamp()
timestamp += timedelta(hours=1)  # Attempting to modify the timestamp

print(event.get_timestamp())  # The original timestamp remains unchanged
# Output: 2023-01-01 10:00:00

