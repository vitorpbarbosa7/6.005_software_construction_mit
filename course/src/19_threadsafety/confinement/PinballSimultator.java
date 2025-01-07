public class PinballSimulator {

  private static PinballSimulator simulator = null;

  private PinballSimulator() {
    System.out.println("created a PinballSimulator object");
  }
  
  public static PinballSimulator getInstance() {
    if (simulator == null){
      simulator = new PinballSimulator();
    }
    return simulator;
  }
}
