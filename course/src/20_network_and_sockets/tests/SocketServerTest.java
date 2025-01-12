import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class SocketServerTest {

    @Test
    public void testUpperCaseLine() throws IOException {
        // Fixed input stream with two lines: "dog" and "cat"
        String inString = "dog\ncat\n";
        ByteArrayInputStream inBytes = new ByteArrayInputStream(inString.getBytes());
        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
        
        // Create BufferedReader from the fixed input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(inBytes));
        PrintWriter out = new PrintWriter(outBytes, true);
        
        // Call the method under test
        SocketServer.upperCaseLine(in, out);
        
        // Check that the expected output was written to outBytes
        String output = outBytes.toString();
        assertEquals("DOG\n", output);  // Check that "dog" was converted to uppercase
        
        // Check that the remaining input is "cat"
        assertEquals("cat", in.readLine());
    }
}

