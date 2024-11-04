class Example:
    class_variable = "Class Level"

    @classmethod
    def show_class_method(cls):
        cls.class_variable = "Modified by class method"
        return f"Class Method: {cls.class_variable}"

    @staticmethod
    def show_static_method():
        return "Static Method: No access to class or instance variables"

# Test class method
print(Example.show_class_method())  # Output: Class Method: Modified by class method

# Test static method
print(Example.show_static_method())  # Output: Static Method: No access to class or instance variables
