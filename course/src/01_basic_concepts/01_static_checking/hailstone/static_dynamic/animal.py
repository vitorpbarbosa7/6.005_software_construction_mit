class Animal:
    def speak(self):
        print("The animal speaks")

my_animal = Animal()
my_animal.fly()  # Error at runtime: 'Animal' object has no attribute 'fly'

