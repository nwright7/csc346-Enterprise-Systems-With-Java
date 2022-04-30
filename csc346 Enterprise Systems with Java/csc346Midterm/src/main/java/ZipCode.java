import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * The ZipCode class serves as the POJO used for step 6
 *
 * @author Nick Wright
 * @since March 2022
 */

public class ZipCode {
    //using @SerializedName to take care of the json elements with spaces in their names
    //without these, the elements with spaces in their names would have null/default values when serialized
    @SerializedName("post code")
    private int postCode;
    private String country;
    @SerializedName("country abbreviation")
    private String countryAbb;
    private ArrayList<Places> places;

    //no constructor will make it default to a default constructor

    //getters and setters
    public int getPostCode() {
        return postCode;
    }
    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbb() {
        return countryAbb;
    }
    public void setCountryAbb(String countryAbb) {
        this.countryAbb = countryAbb;
    }

    public ArrayList<Places> getPlaces() {
        return places;
    }
    public void setPlaces(ArrayList<Places> places) {
        this.places = places;
    }

    //toString
    @Override
    public String toString() {
        return "ZipCode{" +
                "postCode=" + postCode +
                ", country='" + country + '\'' +
                ", countryAbb='" + countryAbb + '\'' +
                ", places=" + places +
                '}';
    }
}//end of ZipCode