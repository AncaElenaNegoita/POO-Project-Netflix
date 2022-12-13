package InputData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"userMovies"})
public class User {
    static final int FREEPREMIUMMOVIES = 15;
    private Credentials credentials;
    private ArrayList<MovieList> userMovies = new ArrayList<>();
    private int tokensCount;
    private int numFreePremiumMovies = FREEPREMIUMMOVIES;
    private ArrayList<MovieList> purchasedMovies = new ArrayList<>();
    private ArrayList<MovieList> watchedMovies = new ArrayList<>();
    private ArrayList<MovieList> likedMovies = new ArrayList<>();
    private ArrayList<MovieList> ratedMovies = new ArrayList<>();

    public User() {
        this.credentials = null;
    }

    public User(User user) {
        this.credentials = user.getCredentials();
        this.userMovies = user.getUserMovies();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials userCredentials) {
        this.credentials = userCredentials;
    }

    public ArrayList<MovieList> getUserMovies() {
        return userMovies;
    }

    public void setUserMovies(ArrayList<MovieList> userMovies) {
        this.userMovies = userMovies;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<MovieList> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<MovieList> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<MovieList> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<MovieList> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<MovieList> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<MovieList> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<MovieList> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<MovieList> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
