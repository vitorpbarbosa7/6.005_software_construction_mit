class TurtleSoup:
    
    @staticmethod
    def calculate_regular_polygon_angle(sides):
        assert sides > 2
        INITIAL_MIN_ANGLE = 180.0
        angle = (sides - 2) * INITIAL_MIN_ANGLE / sides
        return angle

    @staticmethod
    def draw_square(side_length):
        print(f"Drawing a square with side length {side_length}")

    @staticmethod
    def draw_regular_polygon(sides, side_length):
        angle = TurtleSoup.calculate_regular_polygon_angle(sides)
        print(f"Drawing a polygon with {sides} sides, each side {side_length} units long, and interior angle {angle} degrees.")


# Calling static methods without needing an instance
TurtleSoup.draw_square(40)
TurtleSoup.draw_regular_polygon(6, 50)
