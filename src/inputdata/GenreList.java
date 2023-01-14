package inputdata;

/**
 * Stores the number of likes a genre has received
 */
public class GenreList {
    private String genre;
    private int likes;

    /**
     * Getter for genre
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter for genre
     * @return
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Getter for likes
     * @return
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Setter for likes
     * @return
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }
}
