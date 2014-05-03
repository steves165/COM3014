package group1.bean;

/**
 * Class to encapsulate a location search term.
 * @author Daniel Searle <ds00148@surrey.ac.uk>
 */
public class LocationSearchItem {

    /**
     * Location to search using
     */
    private String location;


    /**
     * Get the location of the search term
     * @return location as a string
     */
    public String getLocation() {
        return location;
    }

    /**
     * Constructor to create the parameter
     * @param location Location string to set in the search term
     */
    public LocationSearchItem(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "LocationSearchItem{" + "location=" + location + '}';
    }

}
