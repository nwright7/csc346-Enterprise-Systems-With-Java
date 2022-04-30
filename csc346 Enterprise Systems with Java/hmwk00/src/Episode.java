import java.util.Objects;

/**
 * The episode class holds episodes of a TV show
 *
 * @author Nick Wright
 * @since January 2022
 *
 */

public class Episode implements Comparable<Episode>{
    private String episodeNumber;
    private String episodeTitle;
    private String releaseDate;
    private int runTime;

    //constructor
    //could use this.episodeNumber = episodeNumber, but it is better to use setters
    public Episode(String episodeNumber, String episodeTitle, String releaseDate, int runTime) {
        setEpisodeNumber(episodeNumber);
        setEpisodeTitle(episodeTitle);
        setReleaseDate(releaseDate);
        setRunTime(runTime);
    }

    //getters and setters

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    //toString override
    @Override
    public String toString(){
        String s = String.format("%s, %s, %s, Run Time: %d minutes",
                episodeNumber, episodeTitle, releaseDate, runTime);
        return s;
    }

    //compareTo override
    @Override
    public int compareTo(Episode other) {
        int result = this.episodeTitle.compareTo(other.episodeTitle);
        if(result == 0){
            result = this.episodeNumber.compareTo(other.episodeNumber);
        }
        return result;
    }

    //auto generated hashcode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;
        return runTime == episode.runTime &&
                Objects.equals(episodeNumber, episode.episodeNumber) &&
                Objects.equals(episodeTitle, episode.episodeTitle) &&
                Objects.equals(releaseDate, episode.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeNumber, episodeTitle, releaseDate, runTime);
    }
}
