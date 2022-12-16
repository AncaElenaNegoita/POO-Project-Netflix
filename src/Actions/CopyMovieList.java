package Actions;

import InputData.MovieList;

import java.util.ArrayList;

public class CopyMovieList {
    private static CopyMovieList instance = null;

    public ArrayList<MovieList> getCopiedList (ArrayList<MovieList> movieInputList) {
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

    public static CopyMovieList getInstance() {
        if (instance == null) {
            instance = new CopyMovieList();
        }
        return instance;
    }
}