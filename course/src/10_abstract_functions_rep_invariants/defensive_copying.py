 class ShoppingList:
    def __init__(self, items):
        # Defensive copy on initialization
        self.items = items[:]

    def get_items(self):
        # Defensive copy when returning
        return self.items[:]

# Example usage
shopping_list = ShoppingList(["milk", "bread", "eggs"])
items = shopping_list.get_items()
items.append("butter")  # Modify the returned list

print(shopping_list.get_items())  # The original list is unchanged
# Output: ["milk", "bread", "eggs"]

