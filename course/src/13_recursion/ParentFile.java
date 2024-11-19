import java.io.File;

public class ParentFile {

  public static String fullPathname(File f) {
    
    // Base case, if reach parent
    if (f.getParentFile() == null) {
      return f.getName();
    } else {
      // inductive step, subproblem
      // relate subproblems
      return fullPathname(f.getParentFile()) + "/" + f.getName();
    }
  }

  public static void main(String args[]) {
    File currentDirectory = new File(System.getProperty("user.dir"));

    String fullPath = fullPathname(currentDirectory);
    System.out.println(fullPath);
  }
}
