public class Moirai {
  public static void main(String[] args) {
    // anonymous class
    Thread clotho = new Thread(new Runnable() {
      public void run() { System.out.println("spinning"); };
    });
    clotho.start();
    
    // anonymous class
    new Thread(new Runnable() {
      public void run() { System.out.println("measuring"); };
    }).start();
    
    // anonymous class
    new Thread(new Runnable() {
      public void run() {System.out.println("cutting"); };
    });
  }
}



