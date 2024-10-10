package arrays;
import static java.lang.System.*;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
//	Square brackets to say it is an array
//	Static memory allocation?
	int[] Array_1 = new int[5];
	
	String[] Array_2 = new String[5];
	
	Array_2[0] = "Shapley"; 
	Array_2[1] = "Mutual-Information";
	Array_2[2] = "Entropy";
	Array_2[3] = "Claude-Shannon";
	Array_2[4] = "Andrew-Ng";
	
	String receiveArrayValue = Array_2[4];
	
	int[] exampleNums = {4,7,9,1,2,3};
	
	double[] randomNums = {2.4,2.8};
	
	System.out.println(receiveArrayValue);
	System.out.println(Arrays.toString(exampleNums));
	System.out.println(Arrays.toString(randomNums));
	
//	Sorting array
	int[] unsortedArray = {-99,5,3,7,1,4,2,3};
	
	Arrays.sort(unsortedArray);
	
	for (int arrayItem:unsortedArray) {
		out.println(arrayItem);
	}
	}
}
