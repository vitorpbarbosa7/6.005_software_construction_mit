package classcreation;

// child class - Notebook
// parente class - Computer
public class Notebook extends Computer{

    private int dischargeUnit = -5;
    private int batteryCharge;
    private String aliasName;
    
    public Notebook(String aliasName, int year, int batteryCharge){
        // We must use the sabe constructor
        super(aliasName, year);
        this.batteryCharge = batteryCharge;
        this.aliasName = aliasName;
    }

    public void discharge(){
        this.batteryCharge += dischargeUnit;
        System.out.println("Charge from " 
                            + this.aliasName 
                            + " is "
                            + this.batteryCharge);
    }
}
