class RopeNode:
    def __init__(self, text=""):
        self.text = text
        self.weight = len(text)
        self.left = None
        self.right = None

    def __repr__(self):
        if self.is_leaf():
            return f'[{self.text}]'
        return f'({self.weight})'

    def is_leaf(self):
        return self.left is None and self.right is None


class Rope:
    def __init__(self, text=""):
        self.root = RopeNode(text)

    def insert(self, pos, text):
        """Insert text at the given position."""
        left, right = self._split(self.root, pos)
        new_node = RopeNode(text)
        self.root = self._merge(self._merge(left, new_node), right)

    def delete(self, pos, length):
        """Delete text starting from the given position."""
        left, middle = self._split(self.root, pos)
        _, right = self._split(middle, length)
        self.root = self._merge(left, right)

    def to_string(self):
        """Return the full text represented by the rope."""
        return self._to_string(self.root)

    def display(self):
        """Display the structure of the rope."""
        lines = []
        self._build_tree_string(self.root, 0, False, '-', lines)
        print("\n".join(lines))

    # Internal Helper Methods

    def _to_string(self, node):
        if node is None:
            return ""
        if node.is_leaf():
            return node.text
        return self._to_string(node.left) + self._to_string(node.right)

    def _split(self, node, pos):
        """Split the rope at the given position."""
        if node is None:
            return None, None
        if node.is_leaf():
            if pos <= len(node.text):
                return RopeNode(node.text[:pos]), RopeNode(node.text[pos:])
            else:
                return node, None
        if pos < node.weight:
            left, right = self._split(node.left, pos)
            return left, self._merge(right, node.right)
        else:
            left, right = self._split(node.right, pos - node.weight)
            return self._merge(node.left, left), right

    def _merge(self, left, right):
        """Merge two nodes into a new rope."""
        if left is None:
            return right
        if right is None:
            return left
        new_root = RopeNode()
        new_root.left = left
        new_root.right = right
        new_root.weight = self._calculate_weight(left)
        return new_root

    def _calculate_weight(self, node):
        return node.weight if node else 0

    def _build_tree_string(self, root, curr_index, is_last, prefix, lines):
        """Recursively build tree structure for display."""
        if root is not None:
            lines.append(f"{prefix}{'`-- ' if is_last else '|-- '}{repr(root)}")
            if root.left or root.right:
                if root.left:
                    self._build_tree_string(root.left, curr_index, False, prefix + ('   ' if is_last else '|  '), lines)
                if root.right:
                    self._build_tree_string(root.right, curr_index, True, prefix + ('   ' if is_last else '|  '), lines)


# Interactive Console Program
def main():
    rope = Rope("Hello, World!")
    print("Initial Rope:")
    rope.display()

    while True:
        print("\nCurrent Text:", rope.to_string())
        print("\nOptions:")
        print("1. Insert")
        print("2. Delete")
        print("3. Display Rope Structure")
        print("4. Exit")
        choice = input("Enter choice: ")

        if choice == "1":
            pos = int(input("Enter position to insert: "))
            text = input("Enter text to insert: ")
            rope.insert(pos, text)
            print("\nUpdated Rope:")
            rope.display()
        elif choice == "2":
            pos = int(input("Enter position to delete: "))
            length = int(input("Enter length of text to delete: "))
            rope.delete(pos, length)
            print("\nUpdated Rope:")
            rope.display()
        elif choice == "3":
            print("\nRope Structure:")
            rope.display()
        elif choice == "4":
            print("Exiting...")
            break
        else:
            print("Invalid choice! Please try again.")


if __name__ == "__main__":
    main()

