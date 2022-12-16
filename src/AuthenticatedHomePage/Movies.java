package AuthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import Actions.FactoryChangePageAndOnPage;
import Actions.UserActions;
import InputData.*;

import java.util.ArrayList;

import static java.util.Collections.swap;

public class Movies extends ActionChangePageAndOnPage {
    public Movies() {
        this.nextStates.add(AllPagesEnum.AuthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Movies);
        this.nextStates.add(AllPagesEnum.SeeDetails);
        this.nextStates.add(AllPagesEnum.Logout);
        this.features.put(AllFeaturesEnum.Search, this::search);
        this.features.put(AllFeaturesEnum.Filter, this::filter);
    }

    public ActionChangePageAndOnPage search(Action action) {
        UserActions.filteredMovieList =
                Actions.CopyMovieList.getInstance().getCopiedList(Input.getInstance().getMovies());

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
                System.out.println("l am gasit");
                UserActions.filteredMovieList.add(movie);
            }
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
    }

    public ActionChangePageAndOnPage filter(Action action) {
        System.out.println(Input.getInstance().getMovies().size());
        UserActions.filteredMovieList =
                Actions.CopyMovieList.getInstance().getCopiedList(Input.getInstance().getMovies());

        System.out.println(UserActions.filteredMovieList.size());

        if (UserActions.filteredMovieList.isEmpty()) {
            System.out.println("lista e goala");
            return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
        }

        Sort sort = action.getFilters().getSort();
        Contains contains = action.getFilters().getContains();
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
                        System.out.println(UserActions.filteredMovieList.get(i).getName());
                    }
                }
            }
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
                UserActions.filteredMovieList = copyMovies;
            }
            ok = 0;
            notFound = 0;

            if (action.getFilters().getContains().getGenres() != null) {
                for (MovieList movie : UserActions.filteredMovieList) {
                    for (String genreFilter : action.getFilters().getContains().getGenres()) {
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
            UserActions.filteredMovieList = copyMovies;
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Movies);
    }
}
