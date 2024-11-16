class RealDuck:
    def quack(self):
        print("Quack!")

    def swim(self):
        print("Swimming in the pond!")

class RubberDuck:
    def quack(self):
        print("Squeak!")

    def swim(self):
        print("Floating in the bathtub!")

class Dog:
    def quack(self):
        print("I can't quack like a duck, but I'll bark instead!")

    def swim(self):
        print("Swimming like a dog!")

def make_it_quack_and_swim(duck):
    # Duck typing: no need to check the type of 'duck', just call methods
    duck.quack()
    duck.swim()

# Any object with quack() and swim() methods will work
make_it_quack_and_swim(RealDuck())
make_it_quack_and_swim(RubberDuck())
make_it_quack_and_swim(Dog())

