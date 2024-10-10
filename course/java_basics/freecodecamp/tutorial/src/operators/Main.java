package operators;

public class Main {
	
	public static void main(String[] args) {
		
//		< 
//		>
//		>=
//		<=
//		==
//		!=
		
		int x = 8;
		int y = 7;
		int z = 18;
		
		String nameOneToCompare = new String("abc");
		String nameTwoToCompare = new String("def");
		
		boolean isEqual = x < y;
		boolean isDifferent = x != y;
		boolean isGreaterThan = x > y;
		boolean isLessThan = x < y;
		
		boolean bothTrue = isDifferent && isLessThan;
		
		boolean oneOrAnotherTrue = isEqual || isLessThan;
		
		boolean notTrue = !(isGreaterThan || isLessThan);
		
		boolean isEqualString = nameOneToCompare.equals(nameTwoToCompare);
		
		
		
		String allComparisons;
		
		allComparisons = "IsEqual ?: " + isEqual
				+ "\nIs Different ?: " + isDifferent
				+ "\nIs Greater Than ?: " + isGreaterThan
				+ "\nis Less Than ?: " + isLessThan;
		
		System.out.println(allComparisons);
		
		System.out.println(bothTrue);
		
		System.out.println(oneOrAnotherTrue);
		
		System.out.println(notTrue);
		
		System.out.println("Is Equal ? : " + isEqualString);
	}
	

}
