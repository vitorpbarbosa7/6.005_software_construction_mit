abstract class Shape {
    public abstract double area();
}

class Circle extends Shape {
    private double radius;
    public Circle(double radius) { this.radius = radius; }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width, height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

// BAD approach using instanceof
double calculateTotalArea(List<Shape> shapes) {
    double total = 0;
    for (Shape shape : shapes) {
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            total += Math.PI * circle.getRadius() * circle.getRadius();
        } else if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            total += rectangle.getWidth() * rectangle.getHeight();
        }
    }
    return total;
}


// Correct approach with polymorphism
double calculateTotalArea(List<Shape> shapes) {
    double total = 0;
    for (Shape shape : shapes) {
        total += shape.area();  // Polymorphic call
    }
    return total;
}

