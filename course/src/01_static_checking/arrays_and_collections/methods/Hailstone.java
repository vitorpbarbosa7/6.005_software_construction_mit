import java.util.List;
import java.util.ArrayList;


public class Hailstone {
    /** Compute a hailstone sequence.
     * @param n Starting number for sequence. Assumes n > 0. 
     * @return hailstone sequence starting with n and ending with 1. 
     */
    public static List<Integer> hailstoneSequence (int n) {
        List<Integer> list = new ArrayList<Integer>();
        while (n != 1) {
            list.add(n);
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
        }
        list.add(n);
        return list;
    }

    // we can add the main method here, 
    // inside the class (reminds me of __name__ == __main__)
    // so let's create our ENTRY POINT
    public static void main(String[] args){
        int n = 7;
        // weird, I have to still say what Interface type this is before
        // instantiate the method
        List<Integer> sequence = hailstoneSequence(n);
        System.out.println(sequence);
    }

}

