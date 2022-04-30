/**
 * Public class States stores the data from the original database into reorganized objects
 */

public class States implements Comparable<States>{
    private String city, state, stateAbr;
    private float medianAge, aveHhSize;
    private int males, females, population, veterans, foreignBorn, asian, american, white, hispanic, black;

    //constructor

    public States(String city, String state, float medianAge, int males, int females, int population, int veterans, int foreignBorn, float aveHhSize, String stateAbr, int asian, int american, int white, int hispanic, int black) {
        setCity(city);
        setState(state);
        setMedianAge(medianAge);
        setMales(males);
        setFemales(females);
        setPopulation(population);
        setVeterans(veterans);
        setForeignBorn(foreignBorn);
        setAveHhSize(aveHhSize);
        setStateAbr(stateAbr);
        setAsian(asian);
        setAmerican(american);
        setWhite(white);
        setHispanic(hispanic);
        setBlack(black);
    }


    //setters

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMedianAge(float medianAge) {
        this.medianAge = medianAge;
    }

    public void setMales(int males) {
        this.males = males;
    }

    public void setFemales(int females) {
        this.females = females;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setVeterans(int veterans) {
        this.veterans = veterans;
    }

    public void setForeignBorn(int foreignBorn) {
        this.foreignBorn = foreignBorn;
    }

    public void setAveHhSize(float aveHhSize) {
        this.aveHhSize = aveHhSize;
    }

    public void setStateAbr(String stateAbr) {
        this.stateAbr = stateAbr;
    }

    public void setAsian(int asian) {
        this.asian = asian;
    }

    public void setAmerican(int american) {
        this.american = american;
    }

    public void setWhite(int white) {
        this.white = white;
    }

    public void setHispanic(int hispanic) {
        this.hispanic = hispanic;
    }

    public void setBlack(int black) {
        this.black = black;
    }

    @Override
    public String toString() {
        String s = String.format("%s\t %s\t %f\t %d\t %d\t %d\t %d\t %d\t %f\t %s\t %d\t %d\t %d\t %d\t %d\t",
                state, city, medianAge, males, females, population, veterans, foreignBorn, aveHhSize, stateAbr, american, white, asian, hispanic, black);
        return s;
    }

    @Override
    public int compareTo(States o) {
        return 0;
    }
}
