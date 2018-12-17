package adventure.game;

import adventure.location.Location;
import adventure.location.FunRide;
import adventure.location.Facility;

import java.util.Random;

public class Player {
    //declaring variables
    private Location currentLocation;
    private double money;
    private int energy;
    private int funPoints;


    //Initializing random Generator with
    public static Random randomGenerator = new Random(10);            //initiate seed = 10

    // constructor of Player class
    public Player(Location startLocation) {
        currentLocation = startLocation;
        energy = 60 + randomGenerator.nextInt(201 - 60);            //Random integers are element of [60;200]
        double randomMoney = randomGenerator.nextDouble() * 50;            //Random double are element of [0,00;50,00]
        money = Math.round(randomMoney * 100) / 100.0;
        funPoints = 0;
    }

    //getter Methods of Player class
    public double getMoney() {
        return money;               //returns actual amount of money the player has
    }

    public int getEnergy() {
        return energy;              // returns actual amount of energy the player has
    }

    public int getFunPoints() {
        return funPoints;           // return actual amount of funPoints the player has
    }

    // Override toString Method
    @Override
    public String toString() {
        char euroSign = 0x20ac;
        return String.format("%s%nYou have %d energy and %4.2f%c. " +
                        "You already earned %d fun points.", this.currentLocation, this.energy, this.money,
                euroSign, this.funPoints);
    }

    /* walk method: moves Player towards intended direction, i.e. sets currentLocation to direction
     and makes player loose 10 energy   */
    public void walk(String direction) {
        this.currentLocation = currentLocation.getNeighboringLocation(direction);
        energy -= 10;
    }

    // gameOver method: Player is GameOver. funPoints become 0 and he is set at given location.
    public void gameOver(Location location) {
        currentLocation = location;
        this.funPoints = 0;
    }

    //stay method: Player stays at his currentLocation and accordingly runs either rest or ride.
    public void stay() {
        if (currentLocation.getClass().equals(FunRide.class)) {           //checks if currentLocation is FunRide
            FunRide funRide = (FunRide) currentLocation;
            if (getMoney() >= funRide.getCost() && getEnergy() >= 5) {    //checks if Player has enough money & energy
                this.energy -= 5;                                         //ride costs 5 energy
                this.funPoints += funRide.getFUN_POINTS();                //ride creates funPoints
                this.money -= funRide.getCost();                          //ride costs money
            }
        } else if (currentLocation.getClass().equals(Facility.class)) {   //checks if currentLocation is Facility
            Facility facility = (Facility) currentLocation;
            if (getMoney() >= facility.getCost()) {                       //checks if Player has enough money
                this.energy += facility.getEnergyPoints();                //rest creates energy points
                this.money -= facility.getCost();                         //rest costs money
            }
        }

    }




    /* following methods are not in UML and are just for the sake of simplifying main method.
    They are helping methods to make Game class more readable. */


    //returns currentLocation of Player
    public Location getCurrentLocation() {
        return currentLocation;
    }

    //Checks if input Direction is valid and if player can move in given direction
    public boolean checkValidWalkDirection(String inputDirection) {
        if (inputDirection.equals("up") && getCurrentLocation().getNeighboringLocation("up") != null) return true;
        if (inputDirection.equals("down") && getCurrentLocation().getNeighboringLocation("down") != null) return true;
        if (inputDirection.equals("left") && getCurrentLocation().getNeighboringLocation("left") != null) return true;
        if (inputDirection.equals("right") && getCurrentLocation().getNeighboringLocation("right") != null)
            return true;
        return false;
    }

    //Checks if input Direction is valid for stay(), i.e. String has to equal either "rest" or "ride"
    // and accordingly fit to currentLocation of Player
    public boolean checkValidStayDirection(String inputDirection) {
        if (getCurrentLocation().getClass().equals(FunRide.class)
                && inputDirection.equals("ride")) return true;
        else if (getCurrentLocation().getClass().equals(Facility.class)
                && inputDirection.equals("rest")) return true;
        return false;
    }

    //Method checks if players' energy < 0 and if he hadn't been able to reach parking lot
    public boolean checkPlayerGameOver() {
        Location parkingLot = new Location("Parking Lot");
        if (getEnergy() < 0 && !getCurrentLocation().equals(parkingLot)) return true;
        return false;
    }

    //Prints out gameOver text sequence
    public void runGameOverConsequences() {
        char euroSign = 0x20ac;
        System.out.printf("Game over. You collapse exhausted and the park inspector must carry you\n" +
                "out of the park.\n" +
                "You lose all your fun points! You have %d %c.\n", getFunPoints(), euroSign);
    }

    //Player has won if his currentLocation is parking lot
    //Method to check if he has won
    public boolean checkPlayerHasWon() {
        Location parkingLot = new Location("Parking Lot");
        if (getCurrentLocation().equals(parkingLot)) return true;
        return false;
    }

    //Prints out Winning text sequence
    public void runPlayerHasWonConsequences() {
        char euroSign = 0x20ac;
        System.out.printf("You are here now: Parking lot\n" +
                "Congratulations, you made it. You have collected %d fun points and have\n" +
                "%4.2f %c.\n", getFunPoints(), getMoney(), euroSign);
    }

}