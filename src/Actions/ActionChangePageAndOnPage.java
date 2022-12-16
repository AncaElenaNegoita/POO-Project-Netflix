package Actions;

import InputData.*;

import java.util.*;

public abstract class ActionChangePageAndOnPage {
    // State Design Pattern
    protected interface ApplyFeature {
        ActionChangePageAndOnPage applyFeature(Action action);
    }

    protected Collection<AllPagesEnum> nextStates = new HashSet<>();
    protected Map<AllFeaturesEnum, ApplyFeature> features = new HashMap<>();

    public void copyState(ActionChangePageAndOnPage page) {
        this.nextStates = page.nextStates;
        this.features = page.features;
    }

    public ActionChangePageAndOnPage changePage(Action action) {
        System.out.println("in change");
        String page = action.getPage();
        System.out.println(page);
        if (page.equals("logout")) {
            UserActions.currentUser = new User();
            UserActions.filteredMovieList = new ArrayList<>();
            return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.UnauthenticatedHomePage);
        }
        if (page.equals("movies") && UserActions.currentUser.getCredentials() != null) {
            System.out.println(Input.getInstance().getMovies().get(1).getNumLikes());
            UserActions.filteredMovieList =
                    Actions.CopyMovieList.getInstance().getCopiedList(Input.getInstance().getMovies());
            System.out.println(Input.getInstance().getMovies().get(1).getNumLikes());
        }
        if (page.equals("see details")) {
            MovieList copyMovie = new MovieList();
            if (UserActions.filteredMovieList.isEmpty()) {
                return null;
            }
                for (int i = 0; i < UserActions.filteredMovieList.size(); i++) {
                    MovieList movie = UserActions.filteredMovieList.get(i);
                    if (movie.getName().equals(action.getMovie())) {
                        copyMovie = (MovieList)movie.getClone();
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
                    System.out.println("bye change pe nonnull");
                    UserActions.filteredMovieList.add(copyMovie);
                    return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
                } else {
                    System.out.println("bye change pe null");
                    return null;
                }

        }
        System.out.println("bye change aiciiiiii");
        var nextPage = action.getPageEnum().get(page);
        System.out.println(nextPage);
        if (nextStates.contains(nextPage)) {
            return FactoryChangePageAndOnPage.getInstance().getState(nextPage);
        }
        System.out.println("n-a gasit");
        return null;
    }

    public ActionChangePageAndOnPage onPage(Action action) {
        System.out.println("onpage");
        String applyFeature = action.getFeature();
        var feature = action.getFeatureEnum().get(applyFeature);
        if (features.containsKey(feature)) {
            System.out.println("bye onpage pe good");
            return features.get(feature).applyFeature(action);
        }
        System.out.println("bye onpage pe null");
        return null;
    }
}
