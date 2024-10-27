#include <stdio.h>

int main() {
    int arr[5] = {1, 2, 3, 4, 5};

    // Accessing within the array bounds - this is fine
    printf("Value at arr[2]: %d\n", arr[2]);

    // Accessing out of bounds - this is bad, but C allows it
    // This is accessing memory that is not allocated for the array
    printf("Value at arr[10]: %d\n", arr[10]);

    return 0;
}
