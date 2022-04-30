import com.google.gson.annotations.SerializedName;

/**
 * Places class serves as the sub object for the ZipCode class
 *
 * @author Nick Wright
 * @since March 2022
 */

public class Places {
    //using @SerializedName to take care of the json elements with spaces in their names,
    //without these, the elements with spaces in their names would have null/default values when serialized
    @SerializedName("place name")
    private String placeName;
    private double longitude;
    private String state;
    @SerializedName("state abbreviation")
    private String stateAbbr;
    private double latitude;

    //constructors
    public Places(String placeName, double longitude, String state, String stateAbbr, double latitude){
        setPlaceName(placeName);
        setLongitude(longitude);
        setState(state);
        setStateAbbr(stateAbbr);
        setLatitude(latitude);
    }
    public Places(){
        this("default",-1.0,"default","default",-1.0);
    }

    //getters and setters
    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }
    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    //toString
    @Override
    public String toString() {
        return "Places{" +
                "placeName='" + placeName + '\'' +
                ", longitude=" + longitude +
                ", state='" + state + '\'' +
                ", stateAbbr='" + stateAbbr + '\'' +
                ", latitude=" + latitude +
                '}';
    }
}//end of places