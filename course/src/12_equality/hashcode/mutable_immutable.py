# Immutable objects (Hashable)
print("Hash of integer 42:", hash(42))
print("Hash of float 3.14:", hash(3.14))
print("Hash of string 'example':", hash("example"))
print("Hash of tuple (1, 2, 3):", hash((1, 2, 3)))
print("Hash of frozenset {1, 2, 3}:", hash(frozenset([1, 2, 3])))

# Mutable objects (Unhashable)
try:
    print("Hash of list [1, 2, 3]:", hash([1, 2, 3]))
except TypeError as e:
    print("Error:", e)

try:
    print("Hash of dict {'key': 'value'}:", hash({'key': 'value'}))
except TypeError as e:
    print("Error:", e)

try:
    print("Hash of set {1, 2, 3}:", hash({1, 2, 3}))
except TypeError as e:
    print("Error:", e)

