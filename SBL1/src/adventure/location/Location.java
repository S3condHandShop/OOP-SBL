package adventure.location;


public class Location {
    private String name;
    private Location leftLocation;
    private Location rightLocation;
    private Location upLocation;
    private Location downLocation;

        //define constructor
    public Location(String name) {
        this.name = name;
    }

        //default constructor
    public Location () {
    }

    //method returns name of the Object
    public String getName () {
        return name;
    }


    public Location getNeighbouringLocation(String direction) {
        Location location = new Location();

                switch (direction) {
            case "up": location = upLocation; break;
            case "down": location = downLocation; break;
            case "left": location = leftLocation; break;
            case "right": location = rightLocation; break;
        }
        return location;
    }

    public void createPath(String direction, Location location) {
        if (direction.equals("up")) {
            location.downLocation = this;
            this.upLocation = location;
        } else if (direction.equals("down")) {
            location.upLocation = this;
            this.downLocation = location;
        } else if (direction.equals("right")) {
            location.leftLocation = this;
            this.rightLocation = location;
        } else if (direction.equals("left")) {
            location.rightLocation = this;
            this.leftLocation = location;
        }
    }

        @Override
        public String toString() {
            String str = "You are here: " + this.getName() + ". You can go: ";
            if (getNeighbouringLocation("left")  != null)
                str += "left ";
            if (getNeighbouringLocation("right") != null)
                str += "right ";
            if (getNeighbouringLocation("up") != null)
                str += "up ";
            if (getNeighbouringLocation("down") != null)
                str += "down ";
            return str;
        }

        @Override
    public boolean equals(Object obj) {
        // Check: object isn't null
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
        // Check: Both locations have same name
            Location location = (Location) obj;
            return (this.getName() == location.getName());
    }

    public static void main (String [] args) {
    }
}
