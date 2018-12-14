package adventure.game;

import adventure.game.Player;
import adventure.location.Location;
import adventure.location.Facility;
import adventure.location.FunRide;

import java.util.Scanner;

/*
 * OOP - WS1819 - SBL 1
 * Vorname Nachname (Matrikelnr.)
 * Vorname Nachname (Matrikelnr.)
 */

public class Game {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] argv) {
        // Create Locations
        FunRide rollerCoaster = new FunRide("Roller Coaster", 2.5, 5);
        Facility restroom = new Facility("Restroom", 0.5, 1);
        FunRide bumperCar = new FunRide("Bumper Car", 1, 2);
        Facility kiosk = new Facility("Kiosk", 2, 5);
        FunRide wildWaterChannel = new FunRide("Wild-Water Channel", 2.5, 5);
        Facility restaurant = new Facility("Restaurant", 10, 20);
        FunRide carousel = new FunRide("Carousel", 1, 2);
        FunRide freefallTower = new FunRide("Freefall Tower", 1, 2);
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


        //Create Player with Starting position "rollerCoaster"
        Player playerOne = new Player(rollerCoaster);

        //Game Begins
        //Prologue

        System.out.println("You’re in a theme park, it’s getting dark.");
        System.out.println("You want to go to your car.");
        System.out.println("On the way there you want to have as much fun as possible.");
        System.out.printf("But you have only limited energy and money left.%n%n");


        //Start Rounds
        while (true) {
            /*First of all we check whether a winning condition or a loosing condition has been occurred.
             If so, we accordingly run consequences. */

            //Checks if player has already lost the game, and runs loosing consequences
            if (playerOne.checkPlayerGameOver()) {
                playerOne.runGameOverConsequences();
                playerOne.gameOver(parkingLot);
                break;
            }

            //Check if player has already won the game
            if (playerOne.checkPlayerHasWon()) {
                playerOne.runPlayerHasWonConsequences();
                break;
            }


           /*  Now the rounds really start. First of all we print out player status and then we read in user input and check
           if valid. If so, we accordingly walk Player in input Direction or we let player stay,
            that means we either run ride oder rest.    */

            //Show player status
            System.out.println(playerOne.toString());

            //Start User input
            System.out.print(">: ");
            String inputDirection = scanner.next().toLowerCase();

            //Checks whether walkDirection is valid and moves player in given location
            if (playerOne.checkValidWalkDirection(inputDirection) == true) {
                playerOne.walk(inputDirection);
                continue;
            }

            //Checks whether String inputDirection is valid for stay() and then stays
            if (playerOne.checkValidStayDirection(inputDirection) == true) {
                playerOne.stay();
                continue;
            }
        }
    }
}






