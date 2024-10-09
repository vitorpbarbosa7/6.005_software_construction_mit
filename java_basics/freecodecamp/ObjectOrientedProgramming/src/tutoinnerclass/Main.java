package tutoinnerclass;

public class Main {
	
	private static String displayOutFromClass;
	
	public static void main(String[] args) {
		
		OuterClass out = new OuterClass();
		out.inner();
		
//		out object from OuterClass is alreay instantiated
//		out object has a inner class possibility to be created inside it
//		So the syntax is as follows
		OuterClass.InnerClass innerobject = out.new InnerClass();
		
		
		displayOutFromClass = "Displaying from object created";
		innerobject.display(displayOutFromClass);
		
		System.out.println();
	}

}



˜ 