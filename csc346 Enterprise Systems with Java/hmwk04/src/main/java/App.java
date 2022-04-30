import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program takes state data, reorganizes it and puts it into an array list of state objects, it then
 * returns a tsv file of all the reorganized data.
 *
 * @author: Nick Wright
 * @since: February 2022
 */

public class App {
    public static void main(String[] args) {
        String user;
        String password;
        String host;

        Credentials creds = new Credentials("cscPassword.txt");
        user = creds.getUser();
        System.out.println("The user is: " + creds.getUser());
        password = creds.getPassword();
        System.out.println("The password is hidden!");
        host = creds.getHost();
        System.out.println("The host is: " + creds.getHost());

        ArrayList<String> cityList = new ArrayList<>(600);

        getCityList(cityList, user, host, password);
        showAllCities(cityList, user, host, password);

    }//end of main

    public static void getCityList(ArrayList<String> cityList, String user, String host, String password) {
        System.out.println("\n-=-=-=-=-=-=-=-=-=- In getCityList... -=-=-=-=-=-=-=-=-=-");
        String connectionString = String.format("jdbc:mariadb://%s:3306/misc", host);
        try {
            System.out.println("\n-=-=-=-=-=-=- Adding Cities to ArrayList... -=-=-=-=-=-=-");
            Connection conn = DriverManager.getConnection(connectionString, user, password);

            String query = "SELECT DISTINCT(city), state FROM usCityDemographics ORDER BY city";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String city = rs.getString(1);
                String state = rs.getString(2);
                String line = String.format("%s\t%s", city, state);
                //System.out.println(line);
                cityList.add(line);
            }
            conn.close();
            System.out.println("\n-=-=-=-=-=-=-=-=-= End of getCityList! =-=-=-=-=-=-=-=-=-");

        } catch (SQLException e) {
            System.err.println("ERROR in getCityList(): " + e.getMessage());
            System.exit(1);
        }
    }//end of getCityList

    private static void showAllCities(ArrayList<String> cityList, String user, String host, String password) {
        System.out.println("\n\n-=-=-=-=-=-=-=-=-= In showAllCities... =-=-=-=-=-=-=-=-=-");
        String connectionString = String.format("jdbc:mariadb://%s:3306/misc", host);

        ArrayList<States> states = new ArrayList<States>();

        String dataString;
        try {
            Connection conn = DriverManager.getConnection(connectionString, user, password);
            PrintWriter writer = new PrintWriter("output.tsv");
            writer.println("CITY\tSTATE\tMEDIAN_AGE\tMALES\tFEMALES\tTOTAL_POPULATION\tVETERANS\tFOREIGN_BORN\tAVE_HH_SIZE\tSTATE_ABBR\tAMERICAN\tWHITE\tASIAN\tHISPANIC\tBLACK");

            //creating a prepared statement
            String query = "SELECT * FROM usCityDemographics WHERE city LIKE ? AND state LIKE ? ORDER BY city";
            PreparedStatement stmt = conn.prepareStatement(query);

            for (String line : cityList) {
                String[] fields = line.split("\t");
                //System.out.println(fields[0] + " " + fields[1]);

                stmt.setString(1, fields[0]);
                stmt.setString(2, fields[1]);

                ResultSet rs = stmt.executeQuery();

                //population variables
                int population = 0;
                int asian = 0;
                int american = 0;
                int white = 0;
                int hispanic = 0;
                int black = 0;

                //other variables
                String city = "";
                String state = "";
                float medianAge = 0;
                int males = 0;
                int females = 0;
                int veterans = 0;
                int foreignBorn = 0;
                float aveHhSize = 0;
                String stateAbr = "";
                String race = "";
                int count = 0;

                while (rs.next()) {
                    city = rs.getString("city");
                    state = rs.getString("state");
                    medianAge = rs.getFloat("medianage");
                    males = rs.getInt("males");
                    females = rs.getInt("females");
                    population = rs.getInt("total_population");
                    veterans = rs.getInt("veterans");
                    foreignBorn = rs.getInt("foreign_born");
                    aveHhSize = rs.getFloat("ave_hh_size");
                    stateAbr = rs.getString("state_abbr");
                    race = rs.getString("race");
                    count = rs.getInt("count");


                    if (race.equals("Asian")) {
                        asian = count;
                    } else if (race.equals("American Indian and Alaska Native")) {
                        american = count;
                    } else if (race.equals("White")) {
                        white = count;
                    } else if (race.equals("Hispanic or Latino")) {
                        hispanic = count;
                    } else if (race.equals("Black or African-American")) {
                        black = count;
                    }//end of else if chain
                }//end of while loop

                //add States Object to States Array List
                States st = new States(city, state, medianAge, males, females, population, veterans, foreignBorn, aveHhSize, stateAbr, american, white, asian, hispanic, black);
                states.add(st);
                //System.out.println(st);
                writer.println(st);

            }
            conn.close();
            writer.flush();
            writer.close();
            System.out.println("\n=-=-=-=-=-=-=-=-= End of showAllCities! =-=-=-=-=-=-=-=-=");
        } catch (Exception e) {
            System.err.println("ERROR in showAllCities(): " + e.getMessage());
            System.exit(1);
        }//end of try catch block
    }//end of showAllCities

    //credentials class gets the user, password, and host from the password text file
    static class Credentials {
        String user;
        String password;
        String host;

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
        }
    }//end of credentials
}//end of program
