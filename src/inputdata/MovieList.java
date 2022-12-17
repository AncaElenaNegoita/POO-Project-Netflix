package inputdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"clone", "ratingList", "okPurchase", "okWatch"})
public final class MovieList implements PrototypeMovie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> countriesBanned = new ArrayList<>();
    private int numLikes;
    private double rating = 0.00;
    private int numRatings;
    private ArrayList<Double> ratingList = new ArrayList<>();
    private int okPurchase;
    private int okWatch;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public ArrayList<Double> getRatingList() {
        return ratingList;
    }

    public void setRatingList(final ArrayList<Double> ratingList) {
        this.ratingList = ratingList;
    }

    public int getOkPurchase() {
        return okPurchase;
    }

    public void setOkPurchase(final int okPurchase) {
        this.okPurchase = okPurchase;
    }

    public int getOkWatch() {
        return okWatch;
    }

    public void setOkWatch(final int okWatch) {
        this.okWatch = okWatch;
    }

    public MovieList() {

    }

    public MovieList(final String name, final int year, final int duration,
                     final ArrayList<String> genres, final ArrayList<String> actors,
                     final ArrayList<String> countriesBanned, final int numLikes,
                     final double rating, final int numRatings,
                     final ArrayList<Double> ratingList, final int okPurchase, final int okWatch) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
        this.numLikes = numLikes;
        this.rating = rating;
        this.numRatings = numRatings;
        this.ratingList = ratingList;
        this.okPurchase = okPurchase;
        this.okWatch = okWatch;
    }

    // Prototype Design Pattern

    /**
     * This function creates a new reference for another movie array
     * @return
     */
    @Override
    public PrototypeMovie getClone() {
        return new MovieList(name, year, duration, genres, actors, countriesBanned, numLikes,
                rating, numRatings, ratingList, okPurchase, okWatch);
    }
}
