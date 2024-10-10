package tutoinnerclass;

public class OuterClass {
	
	private String textToDisplay;
	
	public class InnerClass {
		public void display(String textToDisplay) {
			System.out.println(textToDisplay);
		}
	}
	
//	inner method from outerclass is used to instantiate inner class
	public void inner() {
		
		textToDisplay = "Display from inside the class";
		InnerClass in = new InnerClass();
		in.display(textToDisplay);
	}
}

