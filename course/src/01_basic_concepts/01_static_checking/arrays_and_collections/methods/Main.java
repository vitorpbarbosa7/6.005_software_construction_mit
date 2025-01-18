import java.util.List;

// class main 
public class Main {
    // but the entry point is still here
    public static void main(String args[]){
        int n = 7;
        List<Integer> sequence = Hailstone.hailstoneSequence(n);
        System.out.println(sequence);
    }
}
