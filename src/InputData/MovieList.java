package InputData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"clone", "ratingList", "okPurchase", "okWatch"})
public class MovieList implements PrototypeMovie{
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

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public ArrayList<Double> getRatingList() {
        return ratingList;
    }

    public void setRatingList(ArrayList<Double> ratingList) {
        this.ratingList = ratingList;
    }

    public int getOkPurchase() {
        return okPurchase;
    }

    public void setOkPurchase(int okPurchase) {
        this.okPurchase = okPurchase;
    }

    public int getOkWatch() {
        return okWatch;
    }

    public void setOkWatch(int okWatch) {
        this.okWatch = okWatch;
    }

    public MovieList() {

    }

    public MovieList(String name, int year, int duration, ArrayList<String> genres,
                     ArrayList<String> actors, ArrayList<String> countriesBanned, int numLikes,
                     double rating, int numRatings, ArrayList<Double> ratingList, int okPurchase,
                     int okWatch) {
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
    @Override
    public PrototypeMovie getClone() {
        return new MovieList(name, year, duration, genres, actors, countriesBanned, numLikes,
                rating, numRatings, ratingList, okPurchase, okWatch);
    }
}
