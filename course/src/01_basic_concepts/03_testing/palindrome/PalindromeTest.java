import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PalindromeTest {

    @Test
    public void testIsPalindrome() {
        assertEquals(true, Palindrome.isPalindrome("madam"));  // "madam" is a palindrome
        assertEquals(false, Palindrome.isPalindrome("hello"));  // "hello" is not a palindrome
        assertEquals(true, Palindrome.isPalindrome("racecar")); // "racecar" is a palindrome
        assertEquals(true, Palindrome.isPalindrome(""));        // Empty string is a palindrome
    }
}

