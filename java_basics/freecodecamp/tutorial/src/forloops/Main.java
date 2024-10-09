package forloops;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		int incrementLoop = 5;
		int superiorLimitLoop = 50;
		
		for (int i = 0; i <= superiorLimitLoop; i+=incrementLoop) {
			System.out.println(i);
		} 	
		
		
//		array 
		
		int CONSTANT = 5;
		int[] numbersToIterate = {1,7,5,9,6,2,4};
		
		for (int i = 0; i < numbersToIterate.length; i ++) {
			if (numbersToIterate[i] == CONSTANT) {
				System.out.println("Found a " + CONSTANT + " at index " + i);
			}
		}
		
//		Loop in another way
		int count = 0;
//		Like in python, created by Barbara Liskov
		for (int element:numbersToIterate) {
			System.out.println("Element: " + element + " at index: " + count);
			count++;
		}
		
//		Populate a array with for loop
		Scanner sc = new Scanner(System.in);
		String[] names = new String[5];
		for (int i = 0; i < names.length; i++) {
			System.out.print("Input: ");
			String input = sc.nextLine();
			names[i] = input;
			if (i == 2) {
				break;
			}
		}
		
//		Print each name
		for (String item:names) {
			System.out.println(item);
		}
		
	}
}

//Input: Roberta Arcoverde
//Michael
//Robert
//Akita
//Paulo Silveira
//Roberta Arcoverde
