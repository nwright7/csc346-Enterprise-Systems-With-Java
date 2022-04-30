import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import CSC346Utility.Logger;

/**
 * The purpose of this program is to read from a url and print relevant lines
 * while also utilizing a .jar file to output information in a new file.
 *
 * @author Nick Wright
 * @since January 2022
 * */


public class hmwk01 {
    public static Logger log;
    public static void main(String[] args){
        //naming the output file
        Logger log = new Logger("logOutput");
        //logging the start of the program
        log.info("Starting...");
        final String ADDRESS = "https://wttr.in/64079?u";

        readRemoteAddress(ADDRESS);
        System.out.println("\nDone!");
    }//end of main method

    private static void readRemoteAddress(String address) {
        try {
            URL url = new URL(address);
            //logging successful connection
            log.info("Connected...");
            InputStreamReader inStream = new InputStreamReader(url.openStream());
            BufferedReader input = new BufferedReader(inStream);

            String line;
            while((line = input.readLine()) != null){
                line = line.trim();
                //logic to only print in between the pre tags
                if(line.contains("<pre>")){
                    System.out.println("");
                    line = input.readLine();
                    while(!line.contains("</pre>")){
                        System.out.println(line);
                        line = input.readLine();
                    }//end of inner while loop
                }//end of if statement
            }//end of while loop
            input.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            //logging an error
            log.exception(e);
            System.exit(1);
        }
        //ending the log
        log.done();
    }//end of readRemoteAddress
}//end of hmwk01
