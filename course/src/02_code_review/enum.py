from enum import Enum

class Month(Enum):
    JANUARY = 1
    FEBRUARY = 2
    MARCH = 3
    APRIL = 4
    MAY = 5
    JUNE = 6
    JULY = 7
    AUGUST = 8
    SEPTEMBER = 9
    OCTOBER = 10
    NOVEMBER = 11
    DECEMBER = 12

def print_month(current_month):
    if current_month == Month.JANUARY:
        print("It's January!")
    elif current_month == Month.FEBRUARY:
        print("It's February!")
    elif current_month == Month.MARCH:
        print("It's March!")
    else:
        print("It's another month.")

current_month = Month.MARCH
print_month(current_month)

# Loop through all the months
for month in Month:
    print("Month:", month)

