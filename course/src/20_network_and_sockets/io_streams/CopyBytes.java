// input stream
import java.io.FileInputStream;
// output stream
import java.io.FileOutputStream;
// io exception
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException {
        
        // starts at null and after receive something?
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            // close the streams
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}

