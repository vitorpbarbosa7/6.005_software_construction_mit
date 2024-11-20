from typing import Generic, TypeVar

# Define a generic type variable
T = TypeVar('T')

# Define the ImList interface
class ImList(Generic[T]):
    def cons(self, value: T) -> 'ImList[T]':
        raise NotImplementedError("Must be implemented by subclasses")
    
    def first(self) -> T:
        raise NotImplementedError("Must be implemented by subclasses")
    
    def rest(self) -> 'ImList[T]':
        raise NotImplementedError("Must be implemented by subclasses")

# Define the Empty class
class Empty(ImList[T]):
    def __init__(self):
        pass
    
    def cons(self, value: T) -> 'ImList[T]':
        return Cons(value, self)
    
    def first(self) -> T:
        raise ValueError("Empty list has no first element.")
    
    def rest(self) -> 'ImList[T]':
        raise ValueError("Empty list has no rest.")
    
    def __repr__(self):
        return "Empty()"

# Define the Cons class
class Cons(ImList[T]):
    def __init__(self, value: T, rest: 'ImList[T]'):
        self.value = value
        self.rest = rest
    
    def cons(self, value: T) -> 'ImList[T]':
        return Cons(value, self)
    
    def first(self) -> T:
        return self.value
    
    def rest(self) -> 'ImList[T]':
        return self.rest
    
    def __repr__(self):
        return f"Cons({self.value}, {self.rest})"

# Testing the implementation
if __name__ == "__main__":
    empty_list = Empty()
    list_instance = empty_list.cons(1).cons(2).cons(3)  # Creates Cons(3, Cons(2, Cons(1, Empty())))
    print(list_instance)  # Output: Cons(3, Cons(2, Cons(1, Empty())))
    print(list_instance.first())  # Output: 3
    print(list_instance.rest())  # Output: Cons(2, Cons(1, Empty()))

