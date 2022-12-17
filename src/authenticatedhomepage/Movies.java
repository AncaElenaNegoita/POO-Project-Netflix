package authenticatedhomepage;

import actions.ActionChangePageAndOnPage;
import actions.CopyMovieList;
import actions.FactoryChangePageAndOnPage;
import actions.UserActions;
import inputdata.Action;
import inputdata.AllFeaturesEnum;
import inputdata.AllPagesEnum;
import inputdata.Input;
import inputdata.MovieList;
import inputdata.Sort;
import inputdata.Contains;

import java.util.ArrayList;
import java.util.Comparator;

public class Movies extends ActionChangePageAndOnPage {
    public Movies() {
        this.nextStates.add(AllPagesEnum.AuthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Movies);
        this.nextStates.add(AllPagesEnum.SeeDetails);
        this.nextStates.add(AllPagesEnum.Logout);
        this.features.put(AllFeaturesEnum.Search, this::search);
        this.features.put(AllFeaturesEnum.Filter, this::filter);
    }

    /**
     * The search function goes through the array of movies and verifies if a movie starts with
     * the string given in the action. If so, it is added in the filtered movie list.
     * @param action - where the string is situated
     * @return - the state of the current page, which is movies
     */
    public ActionChangePageAndOnPage search(final Action action) {
        UserActions.filteredMovieList =
                actions.CopyMovieList.getInstance().getCopiedList(Input.getInstance().getMovies());

        ArrayList<MovieList> copyList = new ArrayList<>();
        if (UserActions.filteredMovieList.isEmpty()) {
            return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
        }
        for (MovieList movie : UserActions.filteredMovieList) {
            MovieList movieCopy = (MovieList) movie.getClone();
            copyList.add(movieCopy);
        }

        UserActions.filteredMovieList = new ArrayList<>();
        for (MovieList movie : copyList) {
            if (movie.getName().startsWith(action.getStartsWith())) {
                UserActions.filteredMovieList.add(movie);
            }
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
    }

    /**
     * This function filters the list depending on the chosen method. It can be sorted in an
     * increasing or decreasing order by the movie's duration and rating, or it can contain
     * some actors or genres.
     * @param action - where the filter feature is located
     * @return - the state of the next page, in this case Movies (the same current page)
     */
    public ActionChangePageAndOnPage filter(final Action action) {
        UserActions.filteredMovieList =
                actions.CopyMovieList.getInstance().getCopiedList(Input.getInstance().getMovies());

        if (UserActions.filteredMovieList.isEmpty()) {
            return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
        }

        Sort sort = action.getFilters().getSort();
        Contains contains = action.getFilters().getContains();

        if (sort != null) {
            UserActions.filteredMovieList.sort(new Comparator<MovieList>() {
                @Override
                public int compare(final MovieList o1, final MovieList o2) {
                    int comp = 0;

                    if (sort.getDuration() != null) {
                        if (sort.getDuration().equals("increasing")) {
                            comp = o1.getDuration() - o2.getDuration();
                        } else {
                            comp = o2.getDuration() - o1.getDuration();
                        }
                        if (comp != 0) {
                            return comp;
                        }
                    }

                    if (sort.getRating() != null) {
                        if (sort.getRating().equals("increasing")) {
                            comp = (int) o1.getRating() - (int) o2.getRating();
                        } else {
                            comp = (int) o2.getRating() - (int) o1.getRating();
                        }
                        return comp;
                    }

                    return 0;
                }
            });
        }

        if (contains != null) {
            int ok = 0;
            int notFound = 0;
            ArrayList<MovieList> copyMovies = new ArrayList<>();

            if (action.getFilters().getContains().getActors() != null) {
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
            ok = 0;
            notFound = 0;


            if (action.getFilters().getContains().getGenre() != null) {
                copyMovies = new ArrayList<>();
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
            }
            UserActions.filteredMovieList = CopyMovieList.getInstance().getCopiedList(copyMovies);
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
    }
}
