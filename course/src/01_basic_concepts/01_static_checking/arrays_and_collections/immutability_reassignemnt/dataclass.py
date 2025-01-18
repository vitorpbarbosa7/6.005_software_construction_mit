from dataclasses import dataclass

@dataclass(frozen=True)
class Point:
    x: int
    y: int

p = Point(1, 2)
print(p.x)   # Prints: 1
p.x = 10     # Raises a FrozenInstanceError

