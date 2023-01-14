package actions;

import inputdata.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;

/**
 * This class implements the functions change page and on page using the state pattern to get
 * the instance of the next page. If the functions return null, it means that the current page
 * that the current page doesn't point to the page given in the action or an action wasn't
 * found or couldn't be done because of errors.
 */
public abstract class ActionChangePageAndOnPage {
    // State Design Pattern
    protected interface ApplyFeature {
        ActionChangePageAndOnPage applyFeature(Action action);
    }

    protected Collection<AllPagesEnum> nextStates = new HashSet<>();
    protected Map<AllFeaturesEnum, ApplyFeature> features = new HashMap<>();

    /**
     *
     * @param action - the parameter that has the input action, to be more precised the page
     *               where it wants to go.(It will be verified if it is possible to go there)
     * @return - the state of the next page
     */
    public ActionChangePageAndOnPage changePage(final Action action,final String page) {
        if (page.equals("logout") && UserActions.currentUser.getCredentials() != null) {
            if (UserActions.verifyDoAction == 1) {
                UserActions.currentUser = new User();
                UserActions.filteredMovieList = new ArrayList<>();
                UserActions.stackOfPages = new ArrayList<>();
                UserActions.nameOfPages = new ArrayList<>();
            }
            return FactoryChangePageAndOnPage.getInstance().
                    getState(AllPagesEnum.UnauthenticatedHomePage);
        }
        if (page.equals("movies") && UserActions.currentUser.getCredentials() != null) {
            UserActions.filteredMovieList =
                    actions.CopyMovieList.getInstance().
                            getCopiedList(Input.getInstance().getMovies());
        }
        if (page.equals("see details")) {
            UserActions.seeDetailAction.setMovie(action.getMovie());
            MovieList copyMovie = new MovieList();
            if (UserActions.filteredMovieList.isEmpty()) {
                return null;
            }
                for (int i = 0; i < UserActions.filteredMovieList.size(); i++) {
                    MovieList movie = UserActions.filteredMovieList.get(i);
                    if (movie.getName().equals(action.getMovie())) {
                        copyMovie = (MovieList) movie.getClone();
                        ArrayList<MovieList> movieList = Input.getInstance().getMovies();
                        for (int j = 0; j < movieList.size(); j++) {
                            if (movieList.get(j).getName().equals(action.getMovie())) {
                                UserActions.positionMovie = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (copyMovie.getName() != null) {
                    UserActions.filteredMovieList = new ArrayList<>();
                    UserActions.filteredMovieList.add(copyMovie);
                    return FactoryChangePageAndOnPage.getInstance().
                            getState(AllPagesEnum.SeeDetails);
                } else {
                    return null;
                }

        }
        var nextPage = Action.getPageEnum().get(page);
        if (nextStates.contains(nextPage)) {
            return FactoryChangePageAndOnPage.getInstance().getState(nextPage);
        }
        return null;
    }

    /**
     *
     * @param action - the parameter that has the input action, to be more precised the feature
     *               a page has and can do. (It is also verified if the page can do that feature)
     * @return - the state of the next page
     */
    public ActionChangePageAndOnPage onPage(final Action action) {
        String applyFeature = action.getFeature();
        var feature = action.getFeatureEnum().get(applyFeature);
        if (features.containsKey(feature)) {
            return features.get(feature).applyFeature(action);
        }
        return null;
    }
}
