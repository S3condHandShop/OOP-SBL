package adventure.location;

public class Location {
    //declaring variables
    private String name;
    private Location leftLocation;
    private Location rightLocation;
    private Location upLocation;
    private Location downLocation;


    //define constructor
    public Location(String name) {
        this.name = name;
    }

    //define default constructor
    public Location() {

    }

    //method returns the name of the object
    public String getName() {
        return this.name;
    }


    /*- method checks for each passed direction its fitting neighboring location
      - if there is no neighboring location for the passed direction, it returns null
    * */
    public Location getNeighboringLocation(String direction) {
        //- a new location object is generated, so the received neighboring location can be saved and later returned
        Location location = new Location();
        switch (direction) {
            case "up":
                /*- the object of the neighboring locations is equated with new generated location object,
                so it has the same reference like the neighboring location */
                location = upLocation;
                break;
            case "down":
                location = downLocation;
                break;
            case "left":
                location = leftLocation;
                break;
            case "right":
                location = rightLocation;
                break;
        }
        return location; // - method returns an object that points to the same memory as the neighboring location
    }

    /* - method connects two objects with each other
       - to create a path, you have to choose an object(location 1) you use to run this method
       - in the process you have to pass two values to the method:
         the direction from location 1 to location 2 and the target of the direction/location 2 */

    public void createPath(String direction, Location location) {
        /*- if the if-condition matches the passed direction, the location1(this) is equated with the
            target's neighboring object in the oppositional direction (location.downlocation)*/
        switch (direction) {
            case "up":    /*- if the if-condition matches the passed direction,
                 the location1(this) is equated with the target's neighboring object in the oppositional direction */
                location.downLocation = this;
                // - the target/location2 is equated with the neighboring location in the passed direction of location1
                this.upLocation = location;
                break;
            case "down":
                location.upLocation = this;
                this.downLocation = location;
                break;
            case "right":
                location.leftLocation = this;
                this.rightLocation = location;
                break;
            case "left":
                location.rightLocation = this;
                this.leftLocation = location;
                break;
        }
    }

    /* - method overwrites the default toString method and it returns a matching string for the current location
       - the string consists of the name, the player's current location and the next possible targets */
    @Override
    public String toString() {
        String str = "You are here: " +
                this.getName() + ". You can go: ";
        /* - in the if clause the getNeighboringLocation(direction) checks if the neighboring location in the defined
           direction has not been initialized and so we prevent that "null" is going to be in the string */
        if (getNeighboringLocation("left") != null)
            //if the location has been initialized the direction will be printed as a possible direction to walk
            str += "left ";
        if (getNeighboringLocation("right") != null)
            str += "right ";
        if (getNeighboringLocation("up") != null)
            str += "up ";
        if (getNeighboringLocation("down") != null)
            str += "down ";
        //returns complete String
        return str;
    }


    /* - method overwrites the default equals method and returns true if either objects are equal.a
       - They are equal if they are either identical or have the same name */

    @Override
    public boolean equals(Object obj) {
// Check: Object is not null
        if (obj == null) {
            return false;
        }
// Check: Both objects are identical
        if (obj == this) {
            return true;
        }
// Check: Both objects belong to same class
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }
// Check: Both locations have the same name
        Location location = (Location) obj;
        return (this.getName().equals(location.getName()));
    }
}

