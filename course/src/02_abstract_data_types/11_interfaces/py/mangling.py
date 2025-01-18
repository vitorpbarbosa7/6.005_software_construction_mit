class MyClass:
    def __init__(self):
        self.__private_attr = "I am private"

    def __private_method(self):
        print("This is a private method")

    def public_method(self):
        self.__private_method()  # Accessible within the class

obj = MyClass()
print(obj.__private_attr)  # This will raise an AttributeError
obj.__private_method()  # This will also raise an AttributeError

