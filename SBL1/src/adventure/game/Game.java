package adventure.game;

import adventure.game.Player;
import adventure.location.Location;
import adventure.location.Facility;
import adventure.location.FunRide;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.PrintStream;
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

        /*
         * TODO
         */

        //Create Player with Starting position "rollerCoaster"
        Player playerOne = new Player(rollerCoaster);

        //Game Begins
        //Prologue

        System.out.println("You’re in a theme park, it’s getting dark.");
        System.out.println("You want to go to your car.");
        System.out.println("On the way there you want to have as much fun as possible.");
        System.out.printf("But you have only limited energy and money left.%n%n");


        //Show Player status
        //System.out.println(playerOne.toString());


        //Start Rounds
        while (true) {

            //Check if player has already lost the game
            if (playerOne.getEnergy() < 0 && !playerOne.getCurrentLocation().equals(parkingLot)) {
                char euroSign = '\u20ac';
                System.out.printf("Game over. You collapse exhausted and the park inspector must carry you\n" +
                        "out of the park.\n" +
                        "You lose all your fun points! You have %d %c.\n", playerOne.getFunPoints(), euroSign);
                playerOne.gameOver(parkingLot); break;
            }

            //Check if player has already won the game
            if (playerOne.getCurrentLocation().equals(parkingLot)) {
                char euroSign = '\u20ac';
                System.out.printf("You are here now: Parking lot\n" +
                        "Congratulations, you made it. You have collected %d fun points and have\n" +
                        "%4.2f %c.\n", playerOne.getFunPoints(), playerOne.getMoney(), euroSign );
                break;
            }


            //Show player status
            System.out.println(playerOne.toString());

            //Start User input
            System.out.print(">: ");
            String inputDirection = scanner.next().toLowerCase();

            //Check whether inputDirection is valid for walk(), and go in input Direction
            if ( inputDirection.equals("up") && playerOne.getCurrentLocation().getNeighbouringLocation("up") != null)
            { playerOne.walk("up"); continue; }
            if ( inputDirection.equals("down") && playerOne.getCurrentLocation().getNeighbouringLocation("down") != null)
            { playerOne.walk("down"); continue; }
            if ( inputDirection.equals("left") && playerOne.getCurrentLocation().getNeighbouringLocation("left") != null)
            { playerOne.walk("left"); continue; }
            if ( inputDirection.equals("right") && playerOne.getCurrentLocation().getNeighbouringLocation("right") != null)
            { playerOne.walk("right"); continue; }



            //Check whether inputDirection is valid for stay() --> works!
            if (playerOne.getCurrentLocation().getClass().equals(FunRide.class)
                    && inputDirection.equals("ride")) {/*System.out.println("ride works");} */      playerOne.stay(); continue; }
            else if (playerOne.getCurrentLocation().getClass().equals(Facility.class)
                    && inputDirection.equals("rest")) {/*System.out.println("rest works");} */      playerOne.stay(); continue;}
            else continue;

        }


    }
}



/* checks validInputRideOrRest
if (playerOne.getCurrentLocation().equals(FunRide.class) && inputDirection.equals("ride")) { playerOne.stay(); break; }
            else if (playerOne.getCurrentLocation().equals(Facility.class) && inputDirection.equals("rest")) { playerOne.stay(); break;}
*/