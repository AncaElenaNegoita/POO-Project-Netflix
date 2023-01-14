package actions;

import inputdata.Action;
import inputdata.MovieList;

import java.util.ArrayList;

public class FilterAction {
    /**
     * This function filters the list by the criteria given in action and the actor names
     * @param action - where the way of sorting is stored
     */
    public static void filterActor(final Action action) {
        int ok = 0;
        int notFound = 0;
        ArrayList<MovieList> copyMovies = new ArrayList<>();
        for (MovieList movie : UserActions.filteredMovieList) {
            for (String actorFilter : action.getFilters().getContains().getActors()) {
                for (String actorMovie : movie.getActors()) {
                    if (actorMovie.equals(actorFilter)) {
                        ok = 1;
                        break;
                    }
                }
                if (ok != 1) {
                    notFound = 1;
                    break;
                } else {
                    ok = 0;
                }
            }
            if (notFound == 0) {
                copyMovies.add(movie);
            } else {
                notFound = 0;
            }
        }
        UserActions.filteredMovieList = CopyMovieList.getInstance().
                getCopiedList(copyMovies);
    }

    /**
     * This function filters the list by the criteria given in action and the genre
     * @param action - where the way of sorting is stored
     */
    public static void filterGenre(final Action action) {
        int ok = 0;
        int notFound = 0;
        ArrayList<MovieList> copyMovies = new ArrayList<>();
        for (MovieList movie : UserActions.filteredMovieList) {
            for (String genreFilter : action.getFilters().getContains().getGenre()) {
                for (String genreMovie : movie.getGenres()) {
                    if (genreMovie.equals(genreFilter)) {
                        ok = 1;
                        break;
                    }
                }
                if (ok != 1) {
                    notFound = 1;
                    break;
                } else {
                    ok = 0;
                }

            }
            if (notFound == 0) {
                copyMovies.add(movie);
            } else {
                notFound = 0;
            }
        }
        UserActions.filteredMovieList = CopyMovieList.getInstance().getCopiedList(copyMovies);
    }
}
