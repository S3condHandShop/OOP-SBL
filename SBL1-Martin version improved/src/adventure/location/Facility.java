package adventure.location;

public class Facility extends Location {

    //declaring attributes of Facility class
    private final double COST;
    private final int ENERGY_POINTS;

    //define constructor
    public Facility(String name, double cost, int energyPoints) {
        super(name);
        this.COST = Math.round(cost * 100) / 100.0;         //cost are rounded to format XX,XX
        this.ENERGY_POINTS = energyPoints;
    }


    //getter method returning costs
    public double getCost() {
        return this.COST;
    }

    //getter method returning energy points
    public int getEnergyPoints() {
        return this.ENERGY_POINTS;
    }

    // method extends toString method of Location class */
    @Override
    public String toString() {
        char euroSign = 0x20ac;
        return super.toString()
                + String.format("or rest\nA rest cost %4.2f%c and you will receive %d energy points",
                getCost(), euroSign, getEnergyPoints());

    }
}