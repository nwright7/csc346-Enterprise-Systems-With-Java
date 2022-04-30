import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

/**
 * The purpose of this program is to read all the words in a text file
 * and return the count of each word through the use of a TreeMap.
 *
 * @author: Nick Wright
 * @since: March 2022
 */

public class App {
    public static void main(String[] args){
        /*
        //urls
        //http://woz.cs.missouriwestern.edu/data/docs/jabber.txt
        //http://woz.cs.missouriwestern.edu/data/docs/moby.txt
        */

        try{
            //added the address creation within the try block so that it will return an error if an argument isn't provided
            String address = args[0];

            //TreeMap
            Map<String, Integer> frequencies = new TreeMap<>();


            URL url = new URL(address);
            InputStreamReader inStream = new InputStreamReader(url.openStream());
            BufferedReader input = new BufferedReader(inStream);
            String line;

            //while loop to iterate through the text file
            while((line = input.readLine()) != null){
                String[] words = line.split("\\W");
                for(int i = 0; i < words.length; i++){
                    //setting the words to lowercase because program would count capitalized letters seperately
                    String word = words[i].toLowerCase();
                    //this if condition is to get rid of empty strings and blank lines
                    if(word.length() > 0){
                        //if statement to check whether the count of the key needs to be created or updated
                        if(frequencies.containsKey(word)){
                            //the word exists, so we update the count
                            int count = frequencies.get(word);
                            count++;
                            frequencies.put(word, count);
                        }else{
                            //the word does not exist, it is being added to the treemap
                            frequencies.put(word, 1);
                        }
                    }
                }
            }//end of while loop
            printMap(frequencies, "Count of all words in the provided text file");
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }//end of catch

        //call printMap function
        //printMap(frequencies, "Count of all words in the provided text file");
        System.out.println("\nDone!");
    }//end of main

    private static void printMap(Map m, String heading){
        System.out.printf("======= %s =======\n", heading);
        var keys = m.keySet();
        for(var key : keys){
            var value = m.get(key);
            System.out.printf("[%2s]\t%s\n", key, value);
        }
        System.out.printf("======= %d entries\n", m.size());
    }//end of printMap
}
