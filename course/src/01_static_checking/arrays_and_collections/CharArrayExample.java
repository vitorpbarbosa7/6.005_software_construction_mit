public class CharArrayExample {
    // public to be accessible outside of of the class
    // everything is here inside this class 

    // static- like in python, you can make a classmethod, which means it belongs to the class itself
    // So the main method can be invoked without creating an instance of this class here
    public static void main(String[] args) {
        // Declare and initialize a 2D array (array of arrays)
        char[][] charArray = {
            {'A', 'B', 'C'},
            {'D', 'E', 'F'},
            {'G', 'H', 'I'}
        };

        // Accessing elements
        System.out.println("Element at [1][1]: " + charArray[1][1]); // Outputs 'E'

        // Iterating through the 2D array
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                System.out.print(charArray[i][j] + " ");
            }
            System.out.println();
        }
    }
}
