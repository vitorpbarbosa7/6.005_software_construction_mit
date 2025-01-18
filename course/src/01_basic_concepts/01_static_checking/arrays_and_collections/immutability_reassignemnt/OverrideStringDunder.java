public class OverrideStringDunder {
    private String name;

    public OverrideStringDunder(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyClass with name " + name;
    }

    public static void main(String[] args) {
        OverrideStringDunder obj = new OverrideStringDunder ("example");
        System.out.println(obj);  // Calls toString()
    }
}

