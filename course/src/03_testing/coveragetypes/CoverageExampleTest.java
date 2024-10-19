
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CoverageExampleTest {

    // test statement
    @Test
    public void testAbsoluteValue() {
        CoverageExample example = new CoverageExample();
        assertEquals(5, example.absoluteValue(-5)); // Tests the negative case
        assertEquals(5, example.absoluteValue(5));  // Tests the positive case
        assertEquals(0, example.absoluteValue(0));  // Tests the edge case
    }
   
    // test if true and false branches
    @Test
    public void testMax() {
        CoverageExample example = new CoverageExample();
        assertEquals(7, example.max(3, 7)); // Tests the case where a < b
        assertEquals(7, example.max(7, 3)); // Tests the case where a > b
        assertEquals(7, example.max(7, 7)); // Tests the case where a == b
    }

    // will test some values of n
    // BUT IT WILL NOT TEST ALL POSSIBLE VALUES IN THE LOOPING
    // BECAUSE IT WOULD BE EXPONENTIALLY LARGE
    @Test
    public void testLoopExample() {
        CoverageExample example = new CoverageExample();
        example.loopExample(0);  // Edge case: no output
        example.loopExample(1);  // Tests one iteration
        example.loopExample(3);  // Tests multiple iterations
    }
}

