def is_even(n):
    """
    Determines if a number is even using mutual recursion.
    Calls is_odd for odd cases.
    """
    if n == 0:
        return True  # Base case: 0 is even
    return is_odd(n - 1)

def is_odd(n):
    """
    Determines if a number is odd using mutual recursion.
    Calls is_even for even cases.
    """
    if n == 0:
        return False  # Base case: 0 is not odd
    return is_even(n - 1)

# Testing the mutual recursion
for i in range(10):
    print(f"{i} is even? {is_even(i)}, is odd? {is_odd(i)}")

