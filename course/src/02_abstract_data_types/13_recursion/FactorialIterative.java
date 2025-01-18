public class FactorialIterative {

    public static long factorial(int n) {
        long fact = 1;
        for (int i = 1; i<=n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public static void main(String args[]) {

        System.out.println(factorial(5));

    }
}