package adventure.location;

import adventure.location.Location;

public class Facility extends Location {
    private final double COST;
    private final int ENERGY_POINTS;

    //Define Constructor
    public Facility (String name, double cost, int energyPoints) {
        super(name);
        this.COST = Math.round(cost * 100) / 100.0;         //Cost are rounded to format XX,XX, shall we round it?
        this.ENERGY_POINTS = energyPoints;
    }

    /* getter Methods: returns rounded costs and energy points */

    public double getCOST () {
        return COST;
    }
    public int getENERGY_POINTS () {
        return ENERGY_POINTS;
    }

    /* toString Methods extends toString method of Location class */

    @Override
    public String toString () {
        return super.toString() +
                " or rest\nA rest cost "+this.getCOST()+"€ and you will receive "+this.ENERGY_POINTS+" energy points";
    }


    public static void main (String [] args) {
        Facility one = new Facility("Hans", 20.07786734, 4);
        System.out.println(one.toString());
    }
}

