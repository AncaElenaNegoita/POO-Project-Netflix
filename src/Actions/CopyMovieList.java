package Actions;

import InputData.MovieList;

import java.util.ArrayList;

public class CopyMovieList {
    private int ok;
    private static CopyMovieList instance = null;

    public ArrayList<MovieList> getCopiedList (ArrayList<MovieList> movieInputList) {
        ArrayList<MovieList> copyList = new ArrayList<>();
        for (MovieList movie : movieInputList) {
            MovieList movieCopy = (MovieList)movie.getClone();
            for (String country :  movieCopy.getCountriesBanned()) {
                if (UserActions.currentUser.getCredentials().getCountry().equals(country)) {
                    this.setOk(1);
                }
                if (this.getOk() == 0) {
                    copyList.add(movieCopy);
                }
            }
            this.setOk(0);
        }
        this.setOk(1);
        return copyList;
    }

    private CopyMovieList(int ok) {
        this.ok = ok;
    }

    public static CopyMovieList getInstance() {
        if (instance == null) {
            instance = new CopyMovieList(0);
        }
        return instance;
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }
}