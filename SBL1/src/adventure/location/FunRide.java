package adventure.location;

public class FunRide extends Location {

    private final double COST;
    private final int FUN_POINTS;

    //Define Constructor
    public FunRide (String name, double cost, int funPoints) {
        super(name);
        COST = Math.round(cost * 100) / 100.0;              //shall we round here?
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
    public String toString () {
        return super.toString() +
                " or rest\nA ride cost "+this.getCOST()+"€ and you will receive "+this.getFUN_POINTS()+" fun points";
    }
}
