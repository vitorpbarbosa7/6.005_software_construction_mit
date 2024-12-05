public class Moirai {
  public static void main(String[] args) {
    Thread clotho = new Thread(new Runnable() {
      public void run() { System.out.println("spinning"); };
    });
    clotho.start();

    new Thread(new Runnable() {
      public void run() { System.out.println("measuring"); };
    }).start();

    new Thread(new Runnable() {
      public void run() {System.out.println("cutting"); };
    });
  }
}



