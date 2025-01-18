class Circle:
    def __init__(self, radius):
        self.radius = radius

    def area(self):
        return 3.1415 * self.radius * self.radius

class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

# BAD approach using isinstance
def calculate_total_area(shapes):
    total = 0
    for shape in shapes:
        if isinstance(shape, Circle):
            total += 3.1415 * shape.radius ** 2
        elif isinstance(shape, Rectangle):
            total += shape.width * shape.height
    return total


# better:
def calculate_total_area(shapes):
    total = 0
    for shape in shapes:
        total += shape.area()  # Duck typing assumes all shapes have an `area` method
    return total

