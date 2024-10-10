package scanners;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		/* Scanner datatype, a class based as oob?*/
		Scanner sc = new Scanner(System.in);
//		Get next string the user types
//		String scanned = sc.next();
		
//		Get int
//		Expects a integer
//		int scanned = sc.nextInt();
//		try
//        {
//          System.out.println("Enter the two values : ");  
//          Scanner sc = new Scanner(System.in);  
//          double a = sc.nextDouble();  
//          double b = sc.nextDouble();  
//          System.out.println("sum is = " + Double.sum(a, b));  // will return the sum of a and b. 
//        }
//        catch(Exception e)
//        {
//          System.out.println("Invalid Input");
//        }        
		
		String scanned = sc.next();
//		Convert string to integer?
		int parsing_int = Integer.parseInt(scanned);
		
		/********************Print Interface***********************/
//		String print
//		System.out.println(scanned+"suffix");
		
//		int print sum
//		perfect, that's useful
//		System.out.println(scanned+5);
		
//		Double print sum 
//		System.out.println(summation);
		
//		Parsing int
		System.out.println(parsing_int);
		
  	}

}
