package actions;

import inputdata.MovieList;

import java.util.ArrayList;

/**
 * This class implements a method that copies a given array of movies, creating a different
 * reference for the new array.
 */
public class CopyMovieList {
    private static CopyMovieList instance = null;

    /**
     *
     * @param movieInputList - the list of films that needs to be copied
     * @return - the new generated movie list that has only the movies that can be watched in
     * the user's country
     */
    public ArrayList<MovieList> getCopiedList(final ArrayList<MovieList> movieInputList) {
        ArrayList<MovieList> copyList = new ArrayList<>();
        int ok = 0;
        for (MovieList movie : movieInputList) {
            MovieList movieCopy = (MovieList) movie.getClone();
            ok = 0;
            for (String country : movieCopy.getCountriesBanned()) {
                if (UserActions.currentUser.getCredentials().getCountry().equals(country)) {
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                copyList.add(movieCopy);
            }
        }
        return copyList;
    }

    /**
     * The class is made using the Singleton Design Pattern, which means that it is accessible
     * from all the other clases
     * @return - the instance of the class
     */
    public static CopyMovieList getInstance() {
        if (instance == null) {
            instance = new CopyMovieList();
        }
        return instance;
    }
}
