package adventure.location;

public class FunRide extends Location {
    //define final attributes of FunRide class
    private final double COST;
    private final int FUN_POINTS;

    // define constructor
    public FunRide(String name, double cost, int funPoints) {
        super(name);
        this.COST = Math.round(cost * 100) / 100.0;
        this.FUN_POINTS = funPoints;
    }

    //getter method returns COST
    public double getCost() {
        return this.COST;
    }

    //getter method returns FUN_POINTS
    public int getFUN_POINTS() {
        return this.FUN_POINTS;
    }

    //toString method extends toString method of class Location
    @Override
    public String toString() {
        char euroSign = 0x20ac;
        return super.toString() +
                String.format("or ride\nA ride cost %4.2f%c and you will receive %d fun points",
                        getCost(), euroSign, getFUN_POINTS());
    }
}
