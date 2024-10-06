#include <stdio.h>

int main() {
    int a[15];  // Array of fixed size 15
    int i = 0;
    int n = 7;

    // Generate Hailstone sequence and store in the array (no bounds checking)
    while (n != 1 && i < 30) {  // Intentionally set i < 30 to go out of bounds
        a[i] = n;
        i++; 
        if (n % 2 == 0) {
            n = n / 2;
        } else {
            n = 3 * n + 1;
        }
    }

    a[i] = n;  // Store 1 in the array, n=1 is the end of the sequence

    // Printing the array elements (including those outside the bounds)
    printf("Hailstone sequence:\n");
    for (int j = 0; j < 30; j++) {  // Loop beyond the array limit
        printf("%d ", a[j]);  // Accessing out of bounds elements
    }

    return 0;
}

