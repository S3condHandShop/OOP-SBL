package adventure.game;
import adventure.location.Location;
import adventure.location.FunRide;
import adventure.location.Facility;

import java.util.Random;

public class Player {
    private Location currentLocation;
    private double money;
    private int energy;
    private int funPoints;

    public static Random randomGenerator = new Random(10);         //initiate seed = 10

    /* Constructors of adventure.game.Player Class */

    public Player(Location startLocation) {
        currentLocation = startLocation;
        energy = 60 + randomGenerator.nextInt(201 - 60);            //Random integers are element of [60;200]
        double randomMoney = randomGenerator.nextDouble() * 50;       //Random double are element of [0,00;50,00]
        money = Math.round(randomMoney * 100) / 100.0;
        funPoints = 0;
    }

    /* getter Methods of Player class*/

    public double getMoney() {
        return money;               //returns actual amount of money the player has
    }

    public int getEnergy() {
        return energy;              // returns actual amount of energy the player has
    }

    public int getFunPoints() {
        return funPoints;           // return actual amount of funPoints the player has
    }

    /* Override toString Method */

    @Override
    public String toString() {
        return String.format("%s%nYou have %d energy and %4.2f€. " +
                "You already earned %d fun points.", this.currentLocation, this.energy, this.money, this.funPoints);
    }

    /* walk method: moves Player towards the intended direction */

    public void walk(String direction) {
        Location location = new Location();
        this.currentLocation = currentLocation.getNeighbouringLocation(direction);
        energy -= 10;
    }
    /* gameOver method: Player is GameOver. funPoints become 0 and he is set at given location.  */

    public void gameOver(Location location) {
        currentLocation = location;
        this.funPoints = 0;
    }

    public void stay() {
        if (currentLocation.getClass().equals(FunRide.class)) {
            FunRide funRide = (FunRide) currentLocation;
            if (getMoney() >= funRide.getCOST() && getEnergy() >= 5) {
                this.energy -= 5;
                this.funPoints += funRide.getFUN_POINTS();
                this.money -= funRide.getCOST();
            }
        } else if (currentLocation.getClass().equals(Facility.class)) {
            Facility facility = (Facility) currentLocation;
            if (getMoney() >= facility.getCOST()) {
                this.energy += facility.getENERGY_POINTS();
                this.money -= facility.getCOST();
            }
        }

    }

    public Location getCurrentLocation() {
        return currentLocation;
    }       //helper method (not compulsory)


}
