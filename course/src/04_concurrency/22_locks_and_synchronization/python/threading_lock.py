import threading

lock = threading.Lock()

class SharedResource:
    def __init__(self):
        self.value = 0

    def increment(self):
        with lock:  # Acquires and releases the lock automatically
            self.value += 1
            print(f"Value: {self.value}")

shared = SharedResource()

def worker():
    for _ in range(5):
        shared.increment()

threads = [threading.Thread(target=worker) for _ in range(5)]
for t in threads:
    t.start()
for t in threads:
    t.join()

