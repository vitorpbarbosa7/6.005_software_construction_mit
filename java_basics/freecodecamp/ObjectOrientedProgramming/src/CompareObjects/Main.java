package CompareObjects;

public class Main {
	
	public static void main(String[] args) {
		Student joe = new Student("Joe");
		Student bill = new Student("Bill");
		Student tim = new Student("Bill");
		
//		It compares the different objects im memory
		System.out.println(joe.equals(tim));
		System.out.println(bill.equals(tim));
		
		
		System.out.println(joe.compareTo(bill) > 0);
		System.out.println(bill.compareTo(tim));
		
//		__str__ representation
		System.out.println(tim);
//		How actually do this:
	}

}
