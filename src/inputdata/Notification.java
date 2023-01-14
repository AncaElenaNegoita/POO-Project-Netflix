package inputdata;

/**
 * Stores the name of the movie recommended to the premium user and what action happened
 */
public class Notification {
    private String movieName;
    private String message;

    /**
     * Getter for movie name
     * @return
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Setter for movie name
     * @return
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Getter for action
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for action
     * @return
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
