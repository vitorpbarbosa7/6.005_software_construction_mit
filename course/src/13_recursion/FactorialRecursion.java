public class FactorialRecursion {

    public static long factorial(int n) {
        if (n == 0) {
            // Base case
            return 1;
        } else {
            // inductive step, induction
            return factorial(n-1)*n;
        } 
    }

    public static void main(String args[]) {
        System.out.println(factorial(5));
    }
    
}
