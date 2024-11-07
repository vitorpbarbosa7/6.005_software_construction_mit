class Date:
    def __init__(self, day: int, month: int, year: int):
        # Representation: internal fields for storing date
        self._day = day
        self._month = month
        self._year = year

    def __str__(self):
        # Abstraction: the date as a formatted string (abstract value)
        return f"{self._day:02d}/{self._month:02d}/{self._year}"

# Example usage:
date = Date(15, 8, 2023)
print(date)  # Outputs: 15/08/2023 (abstract value)

