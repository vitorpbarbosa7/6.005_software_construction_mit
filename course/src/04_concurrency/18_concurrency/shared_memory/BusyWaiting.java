
public class BusyWaiting {

  private boolean ready = false;
  private int answer = 0;


  // computeAnswer runs in one thread
  private void computeAnswer() {
    answer = 42;
    ready = true;
  }

  // useAnswer runs in a different thread
  private void useAnswer() {
    // stays in the loop while the answer is not ready (ready = false) therefore !ready = true
    while (!ready) {
      Thread.yield();
    }
    if (answer == 0) throw new RuntimeException("answer wasn't ready!");
  }
