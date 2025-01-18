# Immutable object (hashable)
print(hash(10))  # Works

# Mutable object (not hashable)
try:
    print(hash([1, 2, 3]))
except TypeError:
    print("List is not hashable, hence mutable")

