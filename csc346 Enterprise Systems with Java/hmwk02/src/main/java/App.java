import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The purpose of this program is to utilize log4j within a maven project.
 * The program takes courses from a given url and add them to a course arraylist.
 *
 * @author: Nick Wright
 * @since: February 2022
 */

public class App {
    private static final Logger LOG = LogManager.getLogger(App.class);
    public static void main(String[] args){

        final String URL = "http://woz.cs.missouriwestern.edu/csc346/hmwk02.html";

        ArrayList<Course> courses = new ArrayList<Course>();
        LOG.trace("calling readURL");
        readURL(URL, courses);
        LOG.trace("calling printCourses");
        printCourses(courses);

        System.out.println("\nDone\n");
    }//end of main

    private static void readURL(String url, ArrayList<Course> courses) {
        LOG.trace("in readURL");
        try {
            Document doc =  Jsoup.connect(url).get();
            LOG.info("Successfully connected to url!");
            Elements lines = doc.select("tr");  //gets the rows
            for(Element line: lines){   //Each line should be a single course
                //System.out.println(line.select("td"));
                Elements cells = line.select("td");   //The td elements are cells
                //LOG.debug("Cells in line: " + cells.size());  //There should be 4 cells per line.
                if(cells.size() == 4){
                    try{
                        String number = cells.get(0).text();     //pulling the text from all 4 cells.
                        String title = cells.get(1).text();
                        String instructor = cells.get(2).text();
                        int hours = Integer.parseInt(cells.get(3).text());

                        //LOG.debug(String.format("%-10s %-20s %-10s %2d", number, title, instructor, hours));
                        //this would be a good place to add the course to the ArrayList.
                        Course cr = new Course(number, title, instructor, hours);
                        LOG.debug(cr);
                        courses.add(cr);
                        LOG.info("Course ArrayList size = " + courses.size());
                    }catch(Exception e) {
                        //LOG.error("Input could not be parsed, converting to 0. " + "(" + e.getMessage() + ")");
                        String number = cells.get(0).text();     //pulling the text from all 4 cells.
                        String title = cells.get(1).text();
                        String instructor = cells.get(2).text();
                        int hours = 0;
                        //LOG.debug(String.format("%-10s %-20s %-10s %2d", number, title, instructor, hours));
                    //this would be a good place to add the course to the ArrayList.
                    Course cr = new Course(number, title, instructor, hours);
                    LOG.debug(cr);
                    courses.add(cr);
                    LOG.info(courses.size());
                    }//end of inner catch
                }//end of if statement
                Collections.sort(courses);
            }//end of for
        } catch (Exception e) {
            LOG.error("Could not connect.  " + e.getMessage());
            //e.printStackTrace();
        }
    }//end of readURL

    private static void printCourses(ArrayList<Course> courses) {
        LOG.trace("in printCourses\n\n");
        for(Course cr: courses){
            System.out.println(cr);
        }
    }//end of printCourses
}//end of app