package setsandlists;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {
	
	public static void Main(String[] args) {
		
//		"Simple" declarion of set data structure type
		Set<Integer> t = new HashSet<Integer>();
		
		t.add(737);
		t.add(380);
		t.add(787);
		t.add(777);
		t.add(380);
		
		System.out.println(t);
	}

}
