package operations;

public class Main {
	
	public static void main(String[] args) {
		
		/* Declare variable but do not assign immediately value to it */
		/* Local variable were not initialized */
		double x;
		double y;
		double z;
		char _char;
		
		/* Only accepts single char */
		_char = 'b';
		
		/* Assign values */
		x = 6;
		y = 15;
		z = 12;
		
		/* operations */
		/* Fortemente absurdamente tipiada ein, viva o Python */
		double add = x + y + z;
		double mult = x*y*z;
		double div = x/y/z;
		/* can't give us a float or double point if you declare as int */
		double sub = x - y - z; 
		double d = Math.pow(div, mult);
		
		/* Rather than converting */
		/* type casting */
		
		String type_casting_str = "Using type casting to get the correct result  ";
		
		double u = x / (double)y;
		
		/* beautiful, it works */
		String concatenate_str = type_casting_str + u;
		
		System.out.println(x);
		System.out.println(_char);
		System.out.println(add);
		System.out.println(mult);
		System.out.println(div);
		System.out.println(sub);
		System.out.println(d);
		
		
		System.out.println(concatenate_str);
		
	}

}
