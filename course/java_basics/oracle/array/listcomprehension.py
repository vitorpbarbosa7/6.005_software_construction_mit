# List comprehension to square even numbers in a list
numbers = [1, 2, 3, 4, 5, 6]
squared_evens = [x ** 2 for x in numbers if x % 2 == 0]
print(squared_evens)  # Output: [4, 16, 36]
