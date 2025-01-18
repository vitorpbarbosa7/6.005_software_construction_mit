# script.py

# Creator example
class Person:
    def __init__(self, name, age):
        self.name = name  # t (str)
        self.age = age    # t (int)

# Creating a new Person object
p = Person("Alice", 30)
print(f"Created a Person: {p.name}, {p.age}")  # Output: Created a Person: Alice, 30


# Producer example
class Vector:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
    def add(self, other):
        # T + T → T
        return Vector(self.x + other.x, self.y + other.y)

# Creating two Vector objects
v1 = Vector(1, 2)
v2 = Vector(3, 4)

# Producing a new Vector by adding v1 and v2
v3 = v1.add(v2)
print(f"Produced Vector: ({v3.x}, {v3.y})")  # Output: Produced Vector: (4, 6)


# Observer example
class Book:
    def __init__(self, title, author):
        self.title = title
        self.author = author
    
    def get_title(self):
        # T → t (str)
        return self.title

# Creating a Book object
b = Book("1984", "George Orwell")

# Observing the title of the book
print(f"Book title: {b.get_title()}")  # Output: Book title: 1984


# Mutator example
class Counter:
    def __init__(self):
        self.count = 0
    
    def increment(self):
        # T → void
        self.count += 1  # This mutates the `count` field.

    def get_count(self):
        # T → t (int)
        return self.count

# Creating a Counter object
c = Counter()
c.increment()  # Mutates the state of `c`
print(f"Counter value after increment: {c.get_count()}")  # Output: Counter value after increment: 1


