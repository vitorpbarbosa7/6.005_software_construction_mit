




public class AnonymousRunner {


  public static void main(String[] args) {
    new Thread(new Runnable() {
      public void run() {
        System.out.println("Hello from a thread!");
      }
    }).start();
    computFact(100);
  }
