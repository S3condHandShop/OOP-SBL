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
        //Location location = new Location();
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

    /* following methods are not in UML and are just for the sake of simplifying main method.
    They are helping methods to make Game class more readable.
     */

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public boolean checkValidWalkDirection(String inputDirection) {
        if (inputDirection.equals("up") && getCurrentLocation().getNeighbouringLocation("up") != null) return true;
        if (inputDirection.equals("down") && getCurrentLocation().getNeighbouringLocation("down") != null) return true;
        if (inputDirection.equals("left") && getCurrentLocation().getNeighbouringLocation("left") != null) return true;
        if (inputDirection.equals("right") && getCurrentLocation().getNeighbouringLocation("right") != null)
            return true;
        return false;
    }

    public boolean checkValidStayDirection(String inputDirection) {
        if (getCurrentLocation().getClass().equals(FunRide.class)
                && inputDirection.equals("ride")) return true;
        else if (getCurrentLocation().getClass().equals(Facility.class)
                && inputDirection.equals("rest")) return true;
        return false;
    }

    public boolean checkPlayerGameOver() {
        Location parkingLot = new Location("Parking Lot");
        if (getEnergy() < 0 && !getCurrentLocation().equals(parkingLot)) return true;
        return false;
    }

    public void runGameOverConsequences() {
        char euroSign = '\u20ac';
        System.out.printf("Game over. You collapse exhausted and the park inspector must carry you\n" +
                "out of the park.\n" +
                "You lose all your fun points! You have %d %c.\n", getFunPoints(), euroSign);
    }

    public boolean checkPlayerHasWon() {
        Location parkingLot = new Location("Parking Lot");
        if (getCurrentLocation().equals(parkingLot)) return true;
        return false;
    }

    public void runPlayerHasWonConsequences() {
        char euroSign = '\u20ac';
        System.out.printf("You are here now: Parking lot\n" +
                "Congratulations, you made it. You have collected %d fun points and have\n" +
                "%4.2f %c.\n", getFunPoints(), getMoney(), euroSign);
    }


}
