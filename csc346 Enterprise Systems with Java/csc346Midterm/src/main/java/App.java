import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: Nick Wright
 * @since: March 2022
 *
 * This program will take command line arguments and either produce a query or report depending on the parameters passed.
 * It has the capabilities of reading a csv file and creating objects with the use of opencsv, the program can pull data such as
 * location information based off of a zip code, and can use a prepared query to get the latitude and longitude of a specified city.
 *
 * Dependencies Used:
 * Log4j, opencsv, gson, mysql.
 *
 * Command Line arguments being tested:
 * to test query:   query output.json clients.csv 730 eur
 * to test report:  report output.json
 */

public class App {
    //creating logger
    public static final Logger LOG = LogManager.getLogger(App.class);
    public static void main(String[] args){

        //putting args into log
        LOG.info("==== Printing Command Line Arguments ====");
        //using a for loop to read the length of args and print each argument
        for(int i=0; i < args.length; i++){
            LOG.info("args["+i+"] is: "+args[i]);
        }

        //if condition to choose function
        if(args[0].equalsIgnoreCase("query")){
            doQuery(args[2], args[1], args[3], args[4]);
        }else if(args[0].equalsIgnoreCase("report")){
            doReport(args[1]);
        }else{
            System.out.println("Insufficient argument for args[0] provided!");
        }

        LOG.debug("End of program");
    }//end of main

    //this will be the method for queries
    private static void doQuery(String filePath, String reportFile, String clientNumber, String currency){
        LOG.debug("In doQuery...");
        //int to store the index of the requested client (args[3])
        int req = 0;
        //String holding the link to be used with the area code (step 6)
        String areaLink = "https://api.zippopotam.us/us/";

        try{
            //====================================== step 3 ======================================
            LOG.debug("Step 3");
            ArrayList<Clients> clients = (ArrayList<Clients>) new CsvToBeanBuilder(new FileReader(filePath)).withType(Clients.class).build().parse();
            //print the total number of objects
            LOG.info("Records Printed: " + clients.size());
            LOG.info("First Record: " + clients.get(0));

            //====================================== step 4 ======================================
            LOG.debug("Step 4");
            //create gson
            Gson gson4 = new GsonBuilder().setPrettyPrinting().create();
            String s = gson4.toJson(clients);
            LOG.info(s);
            //creating filename
            String fileName = "writeClients.json";
            dump(fileName, gson4.toJson(clients));
            LOG.info("The file has been successfully created! Filename: " + fileName);

            //====================================== step 5 ======================================
            LOG.debug("Step 5");
            //search for provided client number
            for(int i = 0; i < clients.size(); i++){
                if(clients.get(i).getNumber() == Integer.parseInt(clientNumber)){
                    req = i;
                    LOG.info("Specified Client: " + clients.get(req));
                }//end of int statement
            }//end of for loop

            //====================================== step 6 ======================================
            LOG.debug("Step 6");
            //String holding requested postal code
            int areaCode = clients.get(req).getPostalCode();
            //check to see if the postal code needs to have 0's appended to the front
            areaCode = Integer.parseInt(String.format("%05d",clients.get(req).getPostalCode()));
            //append the area code to the end of the link
            areaLink = areaLink+areaCode;
            //get the latitude and longitude of the area code by reading from the link
            //create a url
            URL areaUrl = new URL(areaLink);
            //create a buffered reader
            BufferedReader buffJson = new BufferedReader(new InputStreamReader(areaUrl.openStream()));
            String longLine ="";
            String line;
            while((line = buffJson.readLine()) != null){
                longLine += line.trim();
            }
            Gson gson6 = new Gson();
            ZipCode z = gson6.fromJson(longLine,ZipCode.class);
            double latitude = z.getPlaces().get(0).getLatitude();
            double longitude = z.getPlaces().get(0).getLongitude();
            //log the area code, latitude and longitude
            LOG.info("Area Code: " + areaCode + " | Latitude: " + latitude + " | Longitude: " + longitude);

            //====================================== step 7 ======================================
            LOG.debug("Step 7");
            String user;
            String password;
            String host;
            Credentials creds = new Credentials("cscPassword.txt");
            user = creds.getUser();
            password = creds.getPassword();
            host = creds.getHost();

            //create individual now so that we can pass it to prepared7 and set the median age and population
            Individual indv = new Individual();
            indv.setClient(clients.get(req));
            indv.setLatitude(latitude);
            indv.setLongitude(longitude);

            prepared7(user, password, host, z, indv);

            //================================== step 8 (bonus) ==================================
            LOG.debug("Step 8 (Bonus)");
            String rates = "http://www.floatrates.com/daily/usd.json";
            //calling bonus method and putting the currency information into the individual pojo
            Bonus(rates, currency, indv);
            System.out.printf("");

            //====================================== step 9 ======================================
            LOG.debug("Step 9");
            //create a new json file for the specified client
            Gson gson9 = new GsonBuilder().setPrettyPrinting().create();
            String clientJson = gson9.toJson(indv);
            dump(reportFile, clientJson);

        }catch(Exception e){
            System.err.println("Error in doQuery: " + e);
        }//end of try catch
    }//end of doQuery

    //dump method from Professor Noynaert
    public static void dump(String fileName, String json) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(json);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of dump

    //================================== step 7 class and methods ==================================
    //creating a credentials class to connect to the database for step 7
    static class Credentials{
        String user;
        String password;
        String host;

        //constructor
        public Credentials(String fileName) {
            try {
                Scanner input = new Scanner(new File(fileName));
                password = input.nextLine().trim();
                user = input.nextLine().trim();
                host = input.nextLine().trim();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }//end of constructor

        //getters
        public String getUser() {
            return user;
        }
        public String getPassword() {
            return password;
        }
        public String getHost() {
            return host;
        }
    }//end of Credentials

    private static void prepared7(String user, String password, String host, ZipCode z, Individual indv){
        LOG.debug("In prepared7...");
        String connectionString = String.format("jdbc:mariadb://%s:3306/misc", host);
        try{
            Connection conn = DriverManager.getConnection(connectionString, user, password);

            //we want the median age and the population
            String query = "SELECT medianage, total_population FROM usCityDemographics WHERE city LIKE ? AND state LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            //using setString to replace the question marks with the variables
            stmt.setString(1, z.getPlaces().get(0).getPlaceName()); //getting the city name
            stmt.setString(2, z.getPlaces().get(0).getState()); //getting the state name
            //resultSet
            ResultSet rs = stmt.executeQuery();
            //create variables for the median age and population

            // Code to figure out if the query returned a result
            boolean foundRecord = rs.next();
            if(foundRecord){
                LOG.info("At least one record was found");
            }else{
                LOG.info("No records found, median age and population will remain with the default values of -1");
            }//end of query test

            //while loop to assign values to the median age and population
            while(rs.next()){
                indv.setMedianAge(rs.getFloat("medianage"));
                indv.setPopulation(rs.getInt("total_population"));
            }
            LOG.info("Median Age: " + indv.getMedianAge());
            LOG.info("Population: " + indv.getPopulation());

            //close connection at the end of the try block
            conn.close();
            LOG.debug("End of prepared7");

        }catch(SQLException e){
            System.err.println("Error in prepared7 " + e.getMessage() +"\n"+e);
            System.exit(1);
        }
    }//end of prepared7


    //================================== step 8 method ==================================
    public static void Bonus(String address, String args, Individual indv) throws Exception{
        Gson gson = new Gson();
        URL url = new URL(address);
        String s; //hold the json string
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        s = "";
        String line;
        while((line = br.readLine()) != null){
            s+=line;
        }

        HashMap<String, Map> m = gson.fromJson(s, HashMap.class);
        //getting the name
        Map<String, String> currencyName = m.get(args);
        indv.setCurrName(currencyName.get("name"));
        //getting the rate
        Map<String, Double> currencyRate = m.get(args);
        indv.setCurrRate(currencyRate.get("rate"));
    }//end of Bonus

    //method doReport is used to create a specified report
    public static void doReport(String readFile){
        LOG.debug("In doReport...");
        //pull the data from the report and store it in a pojo. luckily, individual is a pojo that works for this
        try {
            //make a string of the json file
            LOG.debug("Creating Individual from Json...");
            String jsonString;
            jsonString = new String(Files.readAllBytes(Paths.get(readFile)));
            Gson gson = new Gson();
            Individual reportResult = gson.fromJson(jsonString, Individual.class);

            //create markdown file
            LOG.debug("Creating markdown file...");
            String markdown = readFile.replace(".json", ".md");
            PrintWriter output = new PrintWriter(new File(markdown));
            output.printf("# Client report for %s %s\n\n", reportResult.getClient().getFirst(), reportResult.getClient().getLast());
            output.printf("## Client ID %d\n\n", reportResult.getClient().getNumber());
            //writing the address
            output.println("```");
            output.printf(" %s %s\n", reportResult.getClient().getFirst(), reportResult.getClient().getLast());
            output.printf(" %s\n", reportResult.getClient().getAddress());
            output.printf(" %s, %s %s\n", reportResult.getClient().getCity(), reportResult.getClient().getState(), reportResult.getClient().getPostalCode());
            output.println("```");
            //client info
            //the + at the beginning of each string creates a bullet point
            output.println("## Client Info");
            output.println("+ Company: " + reportResult.getClient().getCompany());
            output.println("+ Risk Factor: " + reportResult.getClient().getRiskFactor());
            output.println("+ Phone 1: " + reportResult.getClient().getPhone1());
            output.println("+ Phone 2: " + reportResult.getClient().getPhone2());
            output.println("+ Email: " + reportResult.getClient().getEmail());
            output.println("+ Web: " + reportResult.getClient().getWeb());
            //additional info
            //the - at the beginning of each string creates a bullet point
            output.println("## Additional Info\n");
            output.println("- Latitude: " + reportResult.getLatitude());
            output.println("- Longitude: " + reportResult.getLongitude());
            output.println("- Median Age of Client's City: " + reportResult.getMedianAge());
            output.println("- Population of Client's City: " + reportResult.getPopulation());
            output.println("- Currency Name: " + reportResult.getCurrName());
            output.println("- Currency Rate: " + reportResult.getCurrRate());
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of doReport
}//end of app