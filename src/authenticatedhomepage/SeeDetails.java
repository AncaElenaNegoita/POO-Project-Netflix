package authenticatedhomepage;

import actions.ActionChangePageAndOnPage;
import actions.FactoryChangePageAndOnPage;
import actions.UserActions;
import inputdata.Action;
import inputdata.AllFeaturesEnum;
import inputdata.AllPagesEnum;
import inputdata.Input;

import java.util.ArrayList;

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
    }

    /**
     * This function purchases a film depending on the user's account type and adds it in the
     * user purchased movies array.
     * @param action - where the used parameters are located
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage purchase(final Action action) {
        if (Input.getInstance().getUsers().get(UserActions.positionUser).
                getCredentials().getAccountType().equals("premium")) {
            int number = UserActions.currentUser.getNumFreePremiumMovies();
            if (number != 0) {
                if (UserActions.verifyDoAction == 1) {
                    UserActions.currentUser.setNumFreePremiumMovies(number - 1);
                    Input.getInstance().getUsers().get(UserActions.positionUser).
                            setNumFreePremiumMovies(number - 1);

                    Input.getInstance().getUsers().get(UserActions.positionUser).
                            getPurchasedMovies().add(UserActions.filteredMovieList.get(0));
                    UserActions.filteredMovieList.get(0).setOkPurchase(1);
                    Input.getInstance().getMovies().get(UserActions.positionMovie).
                            setOkPurchase(1);
                }
                return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
            }
        }
        if (UserActions.currentUser.getTokensCount() >= 2) {
            if (UserActions.verifyDoAction == 1) {
                int token = UserActions.currentUser.getTokensCount();
                UserActions.currentUser.setTokensCount(token - 2);
                Input.getInstance().getUsers().get(UserActions.positionUser).
                        setTokensCount(token - 2);

                Input.getInstance().getUsers().get(UserActions.positionUser).
                        getPurchasedMovies().add(UserActions.filteredMovieList.get(0));
            }
        } else {
            return null;
        }
        UserActions.filteredMovieList.get(0).setOkPurchase(1);
        Input.getInstance().getMovies().get(UserActions.positionMovie).setOkPurchase(1);
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    /**
     * This function lets a user watch a film and adds it in the user watched movies array.
     * @param action - where the used parameters are located
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage watch(final Action action) {
        if (UserActions.filteredMovieList.get(0).getOkPurchase() == 0) {
            return null;
        }
        if (UserActions.verifyDoAction == 1) {
            Input.getInstance().getUsers().get(UserActions.positionUser).getWatchedMovies().
                    add(UserActions.filteredMovieList.get(0));
            UserActions.filteredMovieList.get(0).setOkWatch(1);
            Input.getInstance().getMovies().get(UserActions.positionMovie).setOkWatch(1);
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    /**
     * This function lets a user like a film and adds it in the user liked movies array.
     * @param action - where the used parameters are located
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage like(final Action action) {
        if (UserActions.filteredMovieList.get(0).getOkWatch() == 0) {
            return null;
        }
        if (UserActions.verifyDoAction == 1) {
            int like = Input.getInstance().getMovies().get(UserActions.positionMovie).
                    getNumLikes() + 1;
            UserActions.filteredMovieList.get(0).setNumLikes(like);
            Input.getInstance().getMovies().get(UserActions.positionMovie).setNumLikes(like);
            Input.getInstance().getUsers().get(UserActions.positionUser).getLikedMovies().
                    add(UserActions.filteredMovieList.get(0));
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    /**
     * This function lets a user rate a film and adds it in the user rated movies array.
     * @param action - where the used parameters are located
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage rateMovie(final Action action) {
        if (UserActions.filteredMovieList.get(0).getOkWatch() == 0
                || action.getRate() > MAXRATING) {
            return null;
        }

        if (UserActions.verifyDoAction == 1) {
            Input.getInstance().getUsers().get(UserActions.positionUser).getRatedMovies().
                    add(UserActions.filteredMovieList.get(0));

            Input.getInstance().getMovies().get(UserActions.positionMovie).getRatingList().
                    add(action.getRate());
            ArrayList<Double> ratings = Input.getInstance().getMovies().
                    get(UserActions.positionMovie).getRatingList();
            int size = ratings.size();

            double finalRating = 0.00;
            for (Double rate : ratings) {
                finalRating += rate;
            }
            finalRating /= ratings.size();

            UserActions.filteredMovieList.get(0).setRating(finalRating);
            Input.getInstance().getMovies().get(UserActions.positionMovie).setRating(finalRating);

            UserActions.filteredMovieList.get(0).setNumRatings(size);
            Input.getInstance().getMovies().get(UserActions.positionMovie).setNumRatings(size);

        }

        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }
}
