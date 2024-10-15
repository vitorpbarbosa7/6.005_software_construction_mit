# overload

class DataArtist:
    def draw(self, *args):
        if len(args) == 1:
            if isinstance(args[0], str):
                print(f"Drawing string: {args[0]}")
            elif isinstance(args[0], int):
                print(f"Drawing integer: {args[0]}")
            elif isinstance(args[0], float):
                print(f"Drawing float: {args[0]}")
        elif len(args) == 2:
            if isinstance(args[0], int) and isinstance(args[1], float):
                print(f"Drawing integer and float: {args[0]}, {args[1]}")
        else:
            print("Unsupported arguments")

# Example usage:
artist = DataArtist()
artist.draw("hello")       # Drawing string: hello
artist.draw(5)             # Drawing integer: 5
artist.draw(3.14)          # Drawing float: 3.14
artist.draw(5, 3.14)       # Drawing integer and float: 5, 3.14
