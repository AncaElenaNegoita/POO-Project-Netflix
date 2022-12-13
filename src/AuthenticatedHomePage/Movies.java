package AuthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import Actions.FactoryChangePageAndOnPage;
import Actions.UserActions;
import InputData.*;

import java.util.ArrayList;

import static java.util.Collections.swap;

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
        Sort sort = action.getFilters().getSort();
        if (sort != null) {
            int size = UserActions.filteredMovieList.size();
            if (!sort.getRating().isEmpty()) {
                for (int i = 0; i < size - 1; i++) {
                    int k = i;
                    for (int j = i + 1; j < size; j++) {
                        if (sort.getRating().equals("decreasing")) {
                            if (UserActions.filteredMovieList.get(k).getRating() <
                                    UserActions.filteredMovieList.get(j).getRating()) {
                                k = j;
                            }
                        } else if (sort.getRating().equals("increasing")) {
                            if (UserActions.filteredMovieList.get(k).getRating() >
                                    UserActions.filteredMovieList.get(j).getRating()) {
                                k = j;
                            }
                        }
                    }
                    if (k != i) {
                        swap(UserActions.filteredMovieList, i, k);
                    }
                }
            }

            if (!sort.getDuration().isEmpty()) {
                for (int i = 0; i < size - 1; i++) {
                    int k = i;
                    for (int j = i + 1; j < size; j++) {
                        if (sort.getDuration().equals("decreasing")) {
                            if (UserActions.filteredMovieList.get(k).getDuration() <
                                    UserActions.filteredMovieList.get(j).getDuration()) {
                                k = j;
                            }
                        } else if (sort.getDuration().equals("increasing")) {
                            if (UserActions.filteredMovieList.get(k).getDuration() >
                                    UserActions.filteredMovieList.get(j).getDuration()) {
                                k = j;
                            }
                        }
                    }
                    if (k != i) {
                        swap(UserActions.filteredMovieList, i, k);
                    }
                }
            }
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
    }
}
