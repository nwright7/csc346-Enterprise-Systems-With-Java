import com.opencsv.bean.CsvBindByName;

/**
 * The Clients class serves as the POJO for the provided clients.csv file
 *
 * @author Nick Wright
 * @since March 2022
 */

public class Clients {
    //variables
    //using @CsvBindByName to directly state which
    //field in the csv file should be referenced for the variable
    @CsvBindByName(column = "number", required = true)
    private int number;
    @CsvBindByName(column = "first_name", required = true)
    private String first;
    @CsvBindByName(column = "last_name", required = true)
    private String last;
    @CsvBindByName(column = "company", required = true)
    private String company;
    @CsvBindByName(column = "address", required = true)
    private String address;
    @CsvBindByName(column = "city", required = true)
    private String city;
    @CsvBindByName(column = "county", required = true)
    private String county;
    @CsvBindByName(column = "state", required = true)
    private String state;
    @CsvBindByName(column = "postal_code", required = true)
    private int postalCode;
    @CsvBindByName(column = "risk_factor", required = true)
    private double riskFactor;
    @CsvBindByName(column = "phone1", required = true)
    private String phone1;
    @CsvBindByName(column = "phon2", required = true)
    private String phone2;
    @CsvBindByName(column = "email", required = true)
    private String email;
    @CsvBindByName(column = "web", required = true)
    private String web;

    //constructors
    public Clients(int number, String first, String last, String company, String address, String city,
                   String county, String state, int postalCode, double riskFactor,
                   String phone1, String phone2, String email, String web) {
        setNumber(number);
        setFirst(first);
        setLast(last);
        setCompany(company);
        setAddress(address);
        setCity(city);
        setCounty(county);
        setState(state);
        setPostalCode(postalCode);
        setRiskFactor(riskFactor);
        setPhone1(phone1);
        setPhone2(phone2);
        setEmail(email);
        setWeb(web);
    }

    public Clients(){
        this(-1,"Default","Constructor","default","default","default", "default","default",
                -1,-1.0,"111-111-1111","222-222-2222","default","default");
    }

    //getters and setters
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public String getFirst() {
        return first;
    }
    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }
    public void setLast(String last) {
        this.last = last;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public double getRiskFactor() {
        return riskFactor;
    }
    public void setRiskFactor(double riskFactor) {
        this.riskFactor = riskFactor;
    }

    public String getPhone1() {
        return phone1;
    }
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }
    public void setWeb(String web) {
        this.web = web;
    }

    //toString
    @Override
    public String toString() {
        String s = "number=" + number +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", state='" + state + '\'' +
                ", postalCode=" + postalCode +
                ", riskFactor=" + riskFactor +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", email='" + email + '\'' +
                ", web='" + web + '\'';
        return s;
    }
}//end of Clients