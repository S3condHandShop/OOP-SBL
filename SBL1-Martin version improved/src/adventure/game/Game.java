package adventure.game;

import adventure.location.Location;
import adventure.location.Facility;
import adventure.location.FunRide;

import java.util.Scanner;

/*
 * OOP - WS1819 - SBL 1
 * Johannes Falter (1838694)
 * Martin Reimer (2109220)
 */

public class Game {
    public static Scanner scanner = new Scanner(System.in);

    // method prints prologue of the game
    //Helping method to simplify main method
    public static void printPrologue() {
        System.out.print("You’re in a theme park, it’s getting dark.\n" +
                "You want to go to your car.\n" +
                "On the way there you want to have as much fun as possible.\n" +
                "But you have only limited energy and money left.\n\n");
    }


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
        printPrologue();

        //Start Rounds
        while (true) {
            /*First of all we check whether a winning condition or a loosing condition has been occurred.
             If so, we accordingly run consequences. */

            //Checks if player has already lost the game and runs loosing consequences,
            // i.e. checks if energy < 0 and if player hasn't reached parking lot yet
            if (playerOne.checkPlayerGameOver()) {
                playerOne.runGameOverConsequences();
                playerOne.gameOver(parkingLot);
                break;
            }

            //Check if player has already won the game, i.e. checks if player has reached parking lot
            if (playerOne.checkPlayerHasWon()) {
                playerOne.runPlayerHasWonConsequences();        //runs: "You are here now: Parking lot
                // Congratulations, you made it. You have collected X fun points and have XX.XX €."
                break;
            }

            /*
           Now the rounds really start. First of all we print out player status and then we read in user input and
           check if it is valid.
           If so, we let the player walk in the input direction or we let the player stay.
           Stay means, we either run ride oder rest.    */

            //Show player status
            System.out.println(playerOne.toString());

            //Start User input in a loop, to secure a correct input and not spamming the game with playerOne.toString
            while (true) {
                System.out.print(">: ");

                //scans in non case sensitive inputDirection
                String inputDirection = scanner.next().toLowerCase();

                //Checks whether walkDirection is valid and moves player in given location
                if (playerOne.checkValidWalkDirection(inputDirection)) {
                    playerOne.walk(inputDirection);
                    break;
                }

                //Checks whether String inputDirection is valid for stay() and then stays
                if (playerOne.checkValidStayDirection(inputDirection)) {
                    playerOne.stay();
                    break;
                }
            }
        }
    }
}




