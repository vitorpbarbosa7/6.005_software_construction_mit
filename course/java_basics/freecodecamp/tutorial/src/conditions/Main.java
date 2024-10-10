package conditions;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("What is your favorite language ? \n ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		String javaName = new String("Java");
		String pythonName = new String("Python");
		
		if (s.equals(javaName)) { 
			System.out.println(javaName + " is really very famous and useful");
		}
		else if (s.equals(pythonName)){
			System.out.println(pythonName + " is a general purpose simple language");
		}
		else {
			System.out.println("You haven't typed " + javaName + 
					" or " + pythonName);
		}
	}

}
