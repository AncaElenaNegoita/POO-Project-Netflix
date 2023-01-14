package authenticatedhomepage;

import actions.ActionChangePageAndOnPage;
import actions.FactoryChangePageAndOnPage;
import actions.UserActions;
import inputdata.*;

import java.util.ArrayList;
import java.util.Collections;

public class SeeDetails extends ActionChangePageAndOnPage {
    static final double MAXRATING = 5;

    public SeeDetails() {
        this.nextStates.add(AllPagesEnum.AuthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Movies);
        this.nextStates.add(AllPagesEnum.SeeDetails);
        this.nextStates.add(AllPagesEnum.Upgrades);
        this.nextStates.add(AllPagesEnum.Logout);
        this.features.put(AllFeaturesEnum.Purchase, this::purchase);
        this.features.put(AllFeaturesEnum.Watch, this::watch);
        this.features.put(AllFeaturesEnum.Like, this::like);
        this.features.put(AllFeaturesEnum.RateTheMovie, this::rateMovie);
        this.features.put(AllFeaturesEnum.Subscribe, this::subscribe);
    }

    /**
     * This function purchases a film depending on the user's account type and adds it in the
     * user purchased movies array.
     * @param action - where the used parameters are located
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage purchase(final Action action) {
        if (Input.getInstance().getMovies().get(UserActions.positionMovie).getOkPurchase().
                get(UserActions.positionUser) == 1) {
            return null;
        }
        if (Input.getInstance().getUsers().get(UserActions.positionUser).
                getCredentials().getAccountType().equals("premium")) {
            int number = UserActions.currentUser.getNumFreePremiumMovies();
            if (number != 0) {
                if (UserActions.verifyDoAction == 1 && Input.getInstance().getMovies().
                        get(UserActions.positionMovie).getOkPurchase().
                        get(UserActions.positionUser) == 0) {
                    UserActions.currentUser.setNumFreePremiumMovies(number - 1);
                    Input.getInstance().getUsers().get(UserActions.positionUser).
                            setNumFreePremiumMovies(number - 1);
                    Input.getInstance().getMovies().get(UserActions.positionMovie).getOkPurchase().
                            set(UserActions.positionUser, 1);
                    Input.getInstance().getUsers().get(UserActions.positionUser).
                            getPurchasedMovies().add(Input.getInstance().getMovies().
                                    get(UserActions.positionMovie));

                    UserActions.filteredMovieList.get(0).getOkPurchase().
                            set(UserActions.positionUser, 1);
                }
                return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
            }
        }
        if (UserActions.currentUser.getTokensCount() >= 2) {
            if (UserActions.verifyDoAction == 1 && Input.getInstance().getMovies().
                    get(UserActions.positionMovie).getOkPurchase().
                    get(UserActions.positionUser) == 0) {
                int token = UserActions.currentUser.getTokensCount();
                UserActions.currentUser.setTokensCount(token - 2);
                Input.getInstance().getUsers().get(UserActions.positionUser).
                        setTokensCount(token - 2);

                Input.getInstance().getMovies().get(UserActions.positionMovie).getOkPurchase().
                        set(UserActions.positionUser, 1);
                Input.getInstance().getUsers().get(UserActions.positionUser).
                        getPurchasedMovies().add(Input.getInstance().getMovies().
                                get(UserActions.positionMovie));

                UserActions.filteredMovieList.get(0).getOkPurchase().
                        set(UserActions.positionUser, 1);
            }
        } else {
            return null;
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    /**
     * This function lets a user watch a film and adds it in the user watched movies array.
     * @param action - where the used parameters are located
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage watch(final Action action) {
        if (Input.getInstance().getMovies().get(UserActions.positionMovie).getOkPurchase().
                get(UserActions.positionUser) == 0) {
            return null;
        }
        if (UserActions.verifyDoAction == 1 && Input.getInstance().getMovies().
                get(UserActions.positionMovie).getOkWatch().get(UserActions.positionUser) == 0) {
            Input.getInstance().getUsers().get(UserActions.positionUser).getWatchedMovies().
                    add(Input.getInstance().getMovies().get(UserActions.positionMovie));

            UserActions.filteredMovieList.get(0).getOkWatch().set(UserActions.positionUser, 1);
            Input.getInstance().getMovies().get(UserActions.positionMovie).getOkWatch().
                    set(UserActions.positionUser, 1);
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    /**
     * Function that sets the number of likes and memorise it in an array to use for a
     * recommendation.
     */
    public void likeFunction() {
        int like = Input.getInstance().getMovies().get(UserActions.positionMovie).
                getNumLikes() + 1;
        UserActions.filteredMovieList.get(0).setNumLikes(like);
        Input.getInstance().getMovies().get(UserActions.positionMovie).setNumLikes(like);
        Input.getInstance().getUsers().get(UserActions.positionUser).getLikedMovies().
                add(Input.getInstance().getMovies().get(UserActions.positionMovie));
        UserActions.filteredMovieList.get(0).getOkLike().set(UserActions.positionUser, 1);
        Input.getInstance().getMovies().get(UserActions.positionMovie).getOkLike().
                set(UserActions.positionUser, 1);

        int ok;
        User user = Input.getInstance().getUsers().get(UserActions.positionUser);
        for (String genre : UserActions.filteredMovieList.get(0).getGenres()) {
            ok = 0;
            for (int i = 0; i < user.getLikedGenres().size(); i++) {
                if (genre.equals(user.getLikedGenres().get(i).getGenre())) {
                    user.getLikedGenres().get(i).setLikes(user.getLikedGenres().get(i).
                            getLikes() + 1);
                    if (i != 0) {
                        int j;
                        for (j = 0; j < user.getLikedGenres().size(); j++) {
                            GenreList gen = new GenreList();
                            gen.setLikes(user.getLikedGenres().get(i).getLikes());
                            gen.setGenre(user.getLikedGenres().get(i).getGenre());
                            if (user.getLikedGenres().get(j).getLikes() < gen.getLikes()) {
                                user.getLikedGenres().remove(i);
                                user.getLikedGenres().add(j, gen);
                            } else if (user.getLikedGenres().get(j).getLikes()
                                    == gen.getLikes() && user.getLikedGenres().get(i).
                                    getGenre().compareTo(gen.getGenre()) > 0) {
                                user.getLikedGenres().remove(i);
                                user.getLikedGenres().add(j, gen);
                            } else if (user.getLikedGenres().get(i).getGenre().
                                    equals(gen.getGenre())) {
                                break;
                            }
                        }
                        if (user.getLikedGenres().get(i - 1).getLikes() == user.
                                getLikedGenres().get(i).getLikes()) {
                            int verify = user.getLikedGenres().get(i - 1).getGenre().
                                    compareTo(user.getLikedGenres().get(i).getGenre());
                            if (verify > 0) {
                                Collections.swap(user.getLikedGenres(), i - 1, i);
                            }
                        } else if (user.getLikedGenres().get(i - 1).getLikes() < user.
                                getLikedGenres().get(i).getLikes()) {
                            Collections.swap(user.getLikedGenres(), i - 1, i);
                        }
                    }
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                GenreList gen = new GenreList();
                gen.setGenre(genre);
                gen.setLikes(1);
                int size = user.getLikedGenres().size() - 1;
                int i;
                for (i = 0; i < size + 1; i++) {
                    if (user.getLikedGenres().get(i).getLikes() == 1
                            && user.getLikedGenres().get(i).getGenre().
                            compareTo(genre) > 0) {
                        user.getLikedGenres().add(i, gen);
                        break;
                    }
                }
                if (i == size + 1) {
                    user.getLikedGenres().add(gen);
                }
            }
        }
    }

    /**
     * This function lets a user like a film and adds it in the user liked movies array.
     * @param action - where the used parameters are located
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage like(final Action action) {
        if (Input.getInstance().getMovies().get(UserActions.positionMovie).getOkWatch().
                get(UserActions.positionUser) == 0) {
            return null;
        }
        if (UserActions.verifyDoAction == 1 && Input.getInstance().getMovies().
                get(UserActions.positionMovie).getOkLike().get(UserActions.positionUser) == 0) {
            this.likeFunction();
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    /**
     * Function that calculate the new rating of a movie
     * @param action
     * @return - the size of the rating array
     */
    private int rateFunction(final Action action) {
        Input.getInstance().getMovies().get(UserActions.positionMovie).
                getRatingList().set(UserActions.positionUser, action.getRate());

        ArrayList<Double> ratings = Input.getInstance().getMovies().
                get(UserActions.positionMovie).getRatingList();
        int size = 0;
        double finalRating = 0.00;
        for (Double rate : ratings) {
            if (rate != 0) {
                finalRating += rate;
                size++;
            }
        }
        finalRating /= size;
        UserActions.filteredMovieList.get(0).setRating(finalRating);
        Input.getInstance().getMovies().get(UserActions.positionMovie).setRating(finalRating);
        return size;
    }

    /**
     * This function lets a user rate a film and adds it in the user rated movies array.
     * @param action - where the used parameters are located
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage rateMovie(final Action action) {
        if (Input.getInstance().getMovies().get(UserActions.positionMovie).getOkWatch().
                get(UserActions.positionUser) == 0 || action.getRate() > MAXRATING) {
            return null;
        }

        if (UserActions.verifyDoAction == 1) {
            if (Input.getInstance().getMovies().get(UserActions.positionMovie).getOkRate().
                    get(UserActions.positionUser) == 1) {
                this.rateFunction(action);

            } else {
                int size = this.rateFunction(action);

                Input.getInstance().getUsers().get(UserActions.positionUser).getRatedMovies().
                        add(Input.getInstance().getMovies().get(UserActions.positionMovie));

                UserActions.filteredMovieList.get(0).setNumRatings(size);
                Input.getInstance().getMovies().get(UserActions.positionMovie).setNumRatings(size);

                UserActions.filteredMovieList.get(0).getOkRate().set(UserActions.positionUser, 1);
                Input.getInstance().getMovies().get(UserActions.positionMovie).getOkRate().
                        set(UserActions.positionUser, 1);
            }
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    /**
     * This functions lets a user subscribe to a genre
     * @param action
     * @return
     */
    public ActionChangePageAndOnPage subscribe(final Action action) {
        for (String genreAlreadySubscribed : Input.getInstance().getUsers().
                get(UserActions.positionUser).getSubscribedGenres()) {
            if (genreAlreadySubscribed.equals(action.getSubscribedGenre())) {
                return null;
            }
        }
        if (UserActions.verifyDoAction == 1) {
            String getGenre = action.getSubscribedGenre();
            int ok = 0;
            for (String genre : UserActions.filteredMovieList.get(0).getGenres()) {
                if (genre.equals(getGenre)) {
                    Input.getInstance().getUsers().get(UserActions.positionUser).
                            getSubscribedGenres().add(genre);
                    UserActions.currentUser.getSubscribedGenres().add(genre);
                    ok = 1;
                }
            }
            if (ok == 0) {
                return null;
            }
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }
}
