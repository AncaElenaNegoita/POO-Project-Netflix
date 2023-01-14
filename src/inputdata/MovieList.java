package inputdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"clone", "ratingList", "okPurchase", "okWatch"})
public final class MovieList implements PrototypeMovie {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> countriesBanned = new ArrayList<>();
    private int numLikes;
    private double rating = 0.00;
    private int numRatings;
    private ArrayList<Double> ratingList = new ArrayList<>();
    private ArrayList<Integer> okPurchase = new ArrayList<>();
    private ArrayList<Integer> okWatch = new ArrayList<>();
    private ArrayList<Integer> okLike = new ArrayList<>();
    private ArrayList<Integer> okRate = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(final String year) {
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

    public ArrayList<Integer> getOkPurchase() {
        return okPurchase;
    }

    public void setOkPurchase(final ArrayList<Integer> okPurchase) {
        this.okPurchase = okPurchase;
    }

    public ArrayList<Integer> getOkWatch() {
        return okWatch;
    }

    public void setOkWatch(final ArrayList<Integer> okWatch) {
        this.okWatch = okWatch;
    }

    public ArrayList<Integer> getOkLike() {
        return okLike;
    }

    public void setOkLike(final ArrayList<Integer> okLike) {
        this.okLike = okLike;
    }

    public ArrayList<Integer> getOkRate() {
        return okRate;
    }

    public void setOkRate(final ArrayList<Integer> okRate) {
        this.okRate = okRate;
    }

    public MovieList() {

    }

    public MovieList(final String name, final String year, final int duration,
                     final ArrayList<String> genres, final ArrayList<String> actors,
                     final ArrayList<String> countriesBanned, final int numLikes,
                     final double rating, final int numRatings,
                     final ArrayList<Double> ratingList, final ArrayList<Integer> okPurchase,
                     final ArrayList<Integer> okWatch, final ArrayList<Integer> okLike,
                     final ArrayList<Integer> okRate) {
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
        this.okLike = okLike;
        this.okRate = okRate;
    }

    // Prototype Design Pattern

    /**
     * This function creates a new reference for another movie array
     * @return
     */
    @Override
    public PrototypeMovie getClone() {
        return new MovieList(name, year, duration, genres, actors, countriesBanned, numLikes,
                rating, numRatings, ratingList, okPurchase, okWatch, okLike, okRate);
    }
}
