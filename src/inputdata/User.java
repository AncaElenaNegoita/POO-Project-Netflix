package inputdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"subscribedGenres", "likedGenres"})
public final class User {
    static final int FREEPREMIUMMOVIES = 15;
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies = FREEPREMIUMMOVIES;
    private ArrayList<MovieList> purchasedMovies = new ArrayList<>();
    private ArrayList<MovieList> watchedMovies = new ArrayList<>();
    private ArrayList<MovieList> likedMovies = new ArrayList<>();
    private ArrayList<MovieList> ratedMovies = new ArrayList<>();
    private ArrayList<Notification> notifications = new ArrayList<>();
    private ArrayList<String> subscribedGenres = new ArrayList<>();
    private ArrayList<GenreList> likedGenres = new ArrayList<>();

    public User() {
        this.credentials = null;
    }

    public User(final User user) {
        this.credentials = user.getCredentials();
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.purchasedMovies = user.getPurchasedMovies();
        this.watchedMovies = user.getWatchedMovies();
        this.likedMovies = user.getLikedMovies();
        this.ratedMovies = user.getRatedMovies();
        this.notifications = user.getNotifications();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials userCredentials) {
        this.credentials = userCredentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<MovieList> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<MovieList> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<MovieList> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<MovieList> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<MovieList> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<MovieList> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<MovieList> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<MovieList> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(final ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    public ArrayList<GenreList> getLikedGenres() {
        return likedGenres;
    }

    public void setLikedGenres(final ArrayList<GenreList> likedGenres) {
        this.likedGenres = likedGenres;
    }
}
