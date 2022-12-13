package AuthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import Actions.FactoryChangePageAndOnPage;
import Actions.UserActions;
import InputData.Action;
import InputData.AllFeaturesEnum;
import InputData.AllPagesEnum;
import InputData.MovieList;

import java.util.ArrayList;

public class Movies extends ActionChangePageAndOnPage {
    public Movies () {
        this.nextStates.add(AllPagesEnum.AuthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Movies);
        this.nextStates.add(AllPagesEnum.SeeDetails);
        this.nextStates.add(AllPagesEnum.Logout);
        this.features.put(AllFeaturesEnum.Search, this::search);
        this.features.put(AllFeaturesEnum.Filter, this::filter);
    }

    public ActionChangePageAndOnPage search(Action action) {
        ArrayList<MovieList> copyList = new ArrayList<>();
        for (MovieList movie : UserActions.filteredMovieList) {
            MovieList movieCopy = (MovieList)movie.getClone();
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

    public ActionChangePageAndOnPage filter(Action action) {
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
    }
}
