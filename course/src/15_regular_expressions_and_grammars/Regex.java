public class Regex {


  public static void main(String args[]) {

    String input = "The <b>Good</b>, the <i>Bad</i>, and the <strong> Ugly</strong>";
    String regex = "</?[a-z]>";

    String output = input.replaceAll(regex, "");

    System.out.println(input);
    System.out.println(output);

  }
}
