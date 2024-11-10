class Person:
    def __init__(self, first_name: str, last_name: str):
        self.first_name = first_name
        self.last_name = last_name

    def __eq__(self, other):
        if not isinstance(other, Person):
            return False
        return self.last_name.lower() == other.last_name.lower()

    def __hash__(self):
        return hash(self.last_name.lower())

    def __repr__(self):
        return f"Person(first_name='{self.first_name}', last_name='{self.last_name}')"


# Testing the implementation
person1 = Person("Alice", "Smith")
person2 = Person("Bob", "smith")
person3 = Person("Charlie", "Johnson")

# Print equality and hash results
print(f"person1 == person2: {person1 == person2}")
print(f"hash(person1): {hash(person1)}")
print(f"hash(person2): {hash(person2)}")

print("\nperson1 == person3: {person1 == person3}")
print(f"hash(person1): {hash(person1)}")
print(f"hash(person3): {hash(person3)}")

