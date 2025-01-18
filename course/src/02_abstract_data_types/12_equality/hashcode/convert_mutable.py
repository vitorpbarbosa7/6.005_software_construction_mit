class MutableObject:
    def __init__(self, data):
        self.data = data  # Assume `data` is a list, so it's mutable.

    def __hash__(self):
        # Convert the mutable list to an immutable tuple for hashing
        return hash(tuple(self.data))

    def __eq__(self, other):
        return isinstance(other, MutableObject) and self.data == other.data

obj = MutableObject([1, 2, 3])
print("Hash of obj:", hash(obj))

