package editor;

public class SimpleBufferTest extends EditBufferTest {
    /** @see EditBufferTest#make */
    @Override public EditBuffer make() {
        return new SimpleBuffer();
    }
}
