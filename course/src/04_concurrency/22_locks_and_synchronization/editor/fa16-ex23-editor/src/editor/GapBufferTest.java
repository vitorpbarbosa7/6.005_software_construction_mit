package editor;

public class GapBufferTest extends EditBufferTest {    
    /** @see EditBufferTest#make */
    @Override public EditBuffer make() {
        return new GapBuffer();
    }
}
