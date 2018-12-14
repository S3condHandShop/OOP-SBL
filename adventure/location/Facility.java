package adventure.location;


public class Facility extends Location {
    private final double COST;
    private final int ENERGY_POINTS;

    //Define Constructor
    public Facility(String name, double cost, int energyPoints) {
        super(name);
        this.COST = Math.round(cost * 100) / 100.0;         //Cost are rounded to format XX,XX
        this.ENERGY_POINTS = energyPoints;
    }

    /* getter Methods: returns rounded costs and energy points */

    public double getCOST() {
        return COST;
    }

    public int getENERGY_POINTS() {
        return ENERGY_POINTS;
    }

    /* toString Methods extends toString method of Location class */

    @Override
    public String toString() {
        //DecimalFormat decimalFormat = new DecimalFormat("00.00");
        char euroSign = '\u20ac';
        return super.toString()
                + String.format("or rest\nA rest cost %4.2f%c and you will receive %d energy points",
                getCOST(), euroSign, getENERGY_POINTS());

    }
}

