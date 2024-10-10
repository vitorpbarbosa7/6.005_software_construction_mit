class Car:
    # Class variable (shared across all instances)
    manufacturer = "Generic Motors"
    
    def __init__(self, make, model, year):
        # Instance variables (unique for each instance)
        self.make = make
        self.model = model
        self.year = year
        self._speed = 0  # Private variable (conventionally private)
    
    # Public method
    def start(self):
        self._speed = 0  # Local variable in a method
        print(f"{self.make} {self.model} started.")

    # Public method with a parameter
    def accelerate(self, increase):
        # Parameter `increase` is a local variable to this method
        self._speed += increase
        print(f"{self.make} {self.model} accelerating. Speed: {self._speed}")
    
    # Private method (conventionally private, by using _)
    def _check_oil(self):
        print(f"{self.make} {self.model}: Oil level checked.")

# Create instance of Car
car1 = Car("Toyota", "Corolla", 2020)
car1.start()
car1.accelerate(30)

# Access class variable
print(f"Manufacturer: {Car.manufacturer}")

# Accessing private variables and methods is possible, but not recommended
print(f"Speed (accessing private _speed): {car1._speed}")
car1._check_oil()
