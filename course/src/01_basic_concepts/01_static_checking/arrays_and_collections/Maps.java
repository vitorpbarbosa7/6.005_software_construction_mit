// import interface
import java.util.Map;
// import concrete class (real implementation)
import java.util.HashMap;

public class Maps {
    public static void main(String[] args){
        Map<String, Double> treasures = new HashMap<>();
        String x = "palm";
        treasures.put("beach", 25.);
        treasures.put("palm", 50.);
        treasures.put("cove", 75.);
        treasures.put("x", 100.);
        treasures.put("palm", treasures.get("palm") + treasures.size());
        treasures.remove("beach");
        double found = 0;
        for (double treasure : treasures.values()) {
            found += treasure;
}
    }
}