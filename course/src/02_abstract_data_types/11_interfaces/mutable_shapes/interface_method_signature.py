from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self, length, width):
        """
        Calculate the area of the shape.
        
        Parameters:
            length (float): Must be positive.
            width (float): Must be positive.

        Raises:
            ValueError: If length or width is not positive.
        """
        # Precondition check on input values
        if length <= 0 or width <= 0:
            raise ValueError("Length and width must be positive numbers.")

        # This assertion ensures that subclasses respect the input precondition
        assert length > 0 and width > 0, "Length and width must be positive values."

class Rectangle(Shape):
    def area(self, length, width):
        # This automatically inherits the precondition checks from the abstract class
        return length * width

class Triangle(Shape):
    def area(self, base, height):
        # This automatically inherits the precondition checks from the abstract class
        return 0.5 * base * height


rectangle = Rectangle()
print(rectangle.area(5, 3))  # Valid input, calculates area

triangle = Triangle()
print(triangle.area(4, -2))  # Invalid input, raises ValueErro
