package adventure.location;


public class FunRide extends Location {

    private final double COST;
    private final int FUN_POINTS;

    //Define Constructor
    public FunRide(String name, double cost, int funPoints) {
        super(name);
        COST = Math.round(cost * 100) / 100.0;
        FUN_POINTS = funPoints;
    }

    /* getter Methods return COST and FUN_POINTS */
    public double getCOST() {
        return COST;
    }

    public int getFUN_POINTS() {
        return FUN_POINTS;
    }

    //toString method extends toString method of class Location

    @Override
    public String toString() {
        //DecimalFormat decimalFormat = new DecimalFormat("00.00");
        char euroSign = '\u20ac';
        return super.toString() +
                String.format("or ride\nA ride cost %4.2f%c and you will receive %d fun points",
                        this.getCOST(), euroSign, getFUN_POINTS());
    }
}
