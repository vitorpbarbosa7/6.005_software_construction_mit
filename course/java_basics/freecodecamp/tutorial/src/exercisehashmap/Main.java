package exercisehashmap;
import java.util.HashMap;
import java.util.Map;
import static java.lang.System.*;

public class Main {
	
	public static void main(String[] args) {
//		Exercise:
//		Given a string or array of characters, count all into a map?
//		Key equals to the letter, and the value, how many times the letter occurs
		
		String[] saidWords = new String[5];
		Map countWords= new HashMap();
		Integer initialValue = 1;
		Integer plus = 1;
		Integer ocurrenceOfWord;
				
		saidWords[0] = "Shapley";
		saidWords[1] = "Entropy";
		saidWords[2] = "Science";
		saidWords[3] = "Entropy";
		saidWords[4] = "Shapley";
		
		for (String word:saidWords) {
			
			if (!countWords.containsKey(word)) {
				countWords.put(word, initialValue);
				out.println(countWords);
				out.println(countWords.get(word));
			}
			else {
				ocurrenceOfWord = (int) countWords.get(word) + plus;
				countWords.put(word, ocurrenceOfWord);
			}
		}
		
		out.println(countWords);
		
		
		
//		Exercise with chars
		String sentence = "Clean Code";
		Map charCounts = new HashMap();
		
		for (char singleChar:sentence.toCharArray()) {
			out.println(singleChar);
			if (charCounts.containsKey(singleChar)) {
				int old = (int) charCounts.get(singleChar);
				charCounts.put(singleChar, old+1);
			}
			else {
				charCounts.put(singleChar, 1);
			}
		}
			
		out.println(charCounts);
	}
}


