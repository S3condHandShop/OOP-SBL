package adventure.game;
import adventure.location.FunRide;
import adventure.location.Location;

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

    /* getter Methods of adventure.game.Player class*/

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
        this.currentLocation = location.getNeighbouringLocation(direction);                 //Is it right?
        energy -= 10;
    }

    /* gameOver method: Player is GameOver. funPoints become 0 and he is set at given location.  */

    public void gameOver(Location location) {
        currentLocation = location;
        this.funPoints = 0;
    }

    public void stay() {
        // Check if player has enough money & energy points         //Don't know how to check it from Facility class or FunRide class
    }

    public static void main (String [] args) {

        Location rollerCoaster = new Location("Roller Coaster");
        Location restroom = new Location("Restroom");
        Location bumperCar = new Location("Bumper Car");
        Location kiosk = new Location("Kiosk");
        Location wildWaterChannel = new Location("Wild-Water Channel");
        Location restaurant = new Location("Restaurant");
        Location carousel = new Location("Carousel");
        Location freefallTower = new Location("Freefall Tower");
        Location entrance = new Location("Entrance");
        Location parkingLot = new Location("Parking Lot");

        // Create Paths
        rollerCoaster.createPath("up", restroom);
        restroom.createPath("left", bumperCar);
        bumperCar.createPath("down", kiosk);
        bumperCar.createPath("left", wildWaterChannel);
        wildWaterChannel.createPath("down", restaurant);
        restaurant.createPath("down", carousel);
        kiosk.createPath("left", carousel);
        restaurant.createPath("left", freefallTower);
        freefallTower.createPath("down", entrance);
        carousel.createPath("left", entrance);
        entrance.createPath("down", parkingLot);


        Player one = new Player(rollerCoaster);
        System.out.println(one.toString());
        one.walk("up");                     //Direction is null?
        System.out.println(one.toString());
    }


}
