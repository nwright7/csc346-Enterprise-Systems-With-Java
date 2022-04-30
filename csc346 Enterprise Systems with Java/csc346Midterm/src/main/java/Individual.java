/**
 * @author: Nick Wright
 * @since March 2022
 *
 * class Individual is used for step 9
 *
 */

public class Individual {
    private Clients client;
    private double latitude;
    private double longitude;
    private double medianAge;
    private int population;
    private String currName;
    private double currRate;

    //constructor
    public Individual(Clients client, double latitude, double longitude, double medianAge, int population, String currName, double currRate) {
        setClient(client);
        setLatitude(latitude);
        setLongitude(longitude);
        setMedianAge(medianAge);
        setPopulation(population);
        //this.medianAge = -1.0;
        //this.population = -1;
        setCurrName(currName);
        setCurrRate(currRate);
    }//end of constructor

    //default constructor
    public Individual(){
        this(new Clients(), -1.0, -1.0, -1.0, -1, "", -1.0);
    }//end of default constructor

    //getters and setters
        public Clients getClient() {
        return client;
    }
    public void setClient(Clients client) {
        this.client = client;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getMedianAge() {
        return medianAge;
    }
    public void setMedianAge(double medianAge) {
        this.medianAge = medianAge;
    }

    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCurrName() {
        return currName;
    }
    public void setCurrName(String currName) {
        this.currName = currName;
    }

    public double getCurrRate() {
        return currRate;
    }
    public void setCurrRate(double currRate) {
        this.currRate = currRate;
    }

    //toString
    @Override
    public String toString() {
        return "Individual{" +
                "client=" + client +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", medianAge=" + medianAge +
                ", population=" + population +
                '}';
    }
}