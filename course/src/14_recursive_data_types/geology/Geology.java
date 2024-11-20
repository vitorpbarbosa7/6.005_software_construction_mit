// Base class for Geology
abstract class Geology {
    // Base class doesn't need fields; they are defined in subclasses
}

// Core class (leaf of the recursive structure)
class Core extends Geology {
    private int a, b, c, d;

    public Core(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Core(a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ")";
    }
}

// Planet class (contains a Core and its own fields)
class Planet extends Geology {
    private Core core;
    private int a, b, c, d;

    public Planet(Core core, int a, int b, int c, int d) {
        this.core = core;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Planet(core=" + core + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ")";
    }
}

// System class (contains a Geology object, which can be Core, Planet, or another System)
class System extends Geology {
    private Geology geology;
    private int a, b;

    public System(Geology geology, int a, int b) {
        this.geology = geology;
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "System(geology=" + geology + ", a=" + a + ", b=" + b + ")";
    }
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        Core core = new Core(1, 2, 3, 4);
        Planet planet = new Planet(core, 5, 6, 7, 8);
        System solarSystem = new System(planet, 9, 10);
        System recursiveSystem = new System(solarSystem, 11, 12);

        System.out.println(core);              // Core(a=1, b=2, c=3, d=4)
        System.out.println(planet);            // Planet(core=Core(a=1, b=2, c=3, d=4), a=5, b=6, c=7, d=8)
        System.out.println(solarSystem);       // System(geology=Planet(...), a=9, b=10)
        System.out.println(recursiveSystem);   // System(geology=System(...), a=11, b=12)
    }
}

