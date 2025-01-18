#include <stdio.h>
#include <limits.h>

// Function to print the binary representation of an integer
void printBinary(int num) {
    for (int i = 31; i >= 0; i--) {
        int bit = (num >> i) & 1;
        printf("%d", bit);
    }
    printf("\n");
}

int main() {
    int maxInt = INT_MAX; // Maximum positive value for a 32-bit signed int
    int overflow = maxInt + 1; // Causes overflow

    printf("Max int: %d\n", maxInt);
    printf("Binary of max int: ");
    printBinary(maxInt);

    printf("After overflow: %d\n", overflow);
    printf("Binary of overflowed int: ");
    printBinary(overflow);

    return 0;
}

