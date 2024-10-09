package intro;
import static java.lang.System.*;

//
public class Main {
	
//	Method automatically runs in this class
//	if __name__ == "__main__": python equivalent?
	public static void main(String[] args) {
		String computerName = "Macbook Air M1"; 
		int numberOfComputers = 5;
		runComputer(computerName, numberOfComputers);

		int originalValue = 777;
		int addNumber = 380;
		out.println(addValue(originalValue, addNumber));	
	}
	
	
//	Creating methods inside this class:
//	void: returns nothing, but can do something
//	static class, does not need to be instantiated? 
//	as in @staticmethod in python?
	public static void runComputer(String computerName, int numberOfComputers) {
		out.println("Booting: " + 
		numberOfComputers + " " + 
		computerName);
	}
// int : returns type int
	public static int addValue(int originalValue, int addNumber){
		return originalValue + addNumber;
	}
}
