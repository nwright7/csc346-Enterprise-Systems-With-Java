import java.util.Objects;

/**
 * Course.java is a POJO that has the constructor,
 * getters, and setters for a course object.
 *
 * @author Nick Wright
 * @since February 2022
 *
 */

public class Course implements Comparable<Course>{
    private String number;
    private String title;
    private String instructor;
    private int hours;

    //constructor
    public Course(String number, String title, String instructor, int hours){
        setNumber(number);
        setTitle(title);
        setInstructor(instructor);
        setHours(hours);
    }//end of course constructor

    //getters and setters

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    //%-10s\t %s \t %-25s \t %d \n"
    //toString override
    @Override
    public String toString() {
        String s = String.format("|%-10s| |%-30s| |%-12s| |%-2s|",
                number, title, instructor, hours);
        return s;
    }//end of toString

    //compareTo override
    @Override
    public int compareTo(Course other) {
        int result = this.number.compareTo(other.number);
        if(result == 0){
            result = this.instructor.compareTo(other.instructor);
        }
        return result;
    }//end of compareTo

    //auto generated equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return hours == course.hours && Objects.equals(number, course.number) &&
                Objects.equals(title, course.title) &&
                Objects.equals(instructor, course.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, title, instructor, hours);
    }
}//end of course