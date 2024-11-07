from abc import ABC, abstractmethod

class StackADT(ABC):
    """Abstract base class for a stack ADT."""

    @abstractmethod
    def push(self, item):
        """Add an item to the top of the stack."""
        pass

    @abstractmethod
    def pop(self):
        """Remove and return the item at the top of the stack.
        Raises an exception if the stack is empty.
        """
        pass

    @abstractmethod
    def is_empty(self) -> bool:
        """Check if the stack is empty."""
        pass

    @abstractmethod
    def __len__(self) -> int:
        """Return the number of items in the stack."""
        pass

