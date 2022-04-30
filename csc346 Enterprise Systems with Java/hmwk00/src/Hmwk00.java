import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This program will read data of a tv show from a url provided, divide the data by colons and
 * create an episode object of each line that meets the required specifics.
 * It will then store the episode object into an ArrayList and list the episodes in order
 * of title with the tie breaker being the episode number.
 *
 * @author Nick Wright
 * @since Jan 2022
 *
 **/
public class Hmwk00 {
    public static void main(String[] args){

        //creating a variable to hold the web address we are pulling the data from
        final String ADDRESS = "https://raw.githubusercontent.com/noynaert/csc346handouts/main/dataFiles/episodes.txt";

        ArrayList<Episode> lines = new ArrayList<Episode>();
        readRemoteAddress(ADDRESS, lines);
        
        printList(lines);

        System.out.println("\nDone!\n");
    }//end of main

    private static void printList(ArrayList<Episode> episodes) {
        System.out.println("-------Episode Data--------");
        for(Episode ep: episodes){
            System.out.println(ep);
        }
    }//end of printList

    private static void readRemoteAddress(String address, ArrayList<Episode> lines) {
        try {
            URL url = new URL(address);

            InputStreamReader inStream = new InputStreamReader(url.openStream());
            BufferedReader input = new BufferedReader(inStream);
            //**************** adding this initial readLine to skip over the first line ******************
            input.readLine();

            String line;
            while ( (line = input.readLine()) != null ){
                //convert the string line into an episode object to be put into the ArrayList
                String[] fields = line.split(":");
                if(fields.length == 4){
                    String episode = fields[0];
                    String title = fields[1];
                    String releaseDate = fields[2];
                    int runTime = Integer.parseInt(fields[3]);
                    Episode ep = new Episode(episode, title, releaseDate, runTime);
                    lines.add(ep);
                }
            }//end of while loop
            //close the input
            input.close();
            Collections.sort(lines);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
            //e.printStackTrace();
        }
    }//end of readRemoteAddress

    //functionality to parse the date and convert it to an instance of LocalDate.
    public static LocalDate parseDate(String usDate) {
        LocalDate date = null;
        String[] fields = usDate.split("/");
        int month = Integer.parseInt(fields[0]);
        int day = Integer.parseInt(fields[1]);
        int year = Integer.parseInt(fields[2]);

        date = LocalDate.of(year, month, day);

        return date;
    }//end of LocalDate
}//end of hmwk00

