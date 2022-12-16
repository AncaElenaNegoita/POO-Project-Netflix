package AuthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import Actions.FactoryChangePageAndOnPage;
import Actions.UserActions;
import InputData.Action;
import InputData.AllFeaturesEnum;
import InputData.AllPagesEnum;
import InputData.Input;

import java.util.ArrayList;

public class SeeDetails extends ActionChangePageAndOnPage {
    static final double MAXRATING = 5;

    public SeeDetails () {
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

    public ActionChangePageAndOnPage purchase(Action action) {
        System.out.println("am intrat pe purchase");
        if (!action.getMovie().equals(UserActions.filteredMovieList.get(0).getName())) {
            System.out.println("nu e bn la purchase 1 cond");
            return null;
        }
        if (UserActions.currentUser.getCredentials().getAccountType().equals("premium")) {
            int number = UserActions.currentUser.getNumFreePremiumMovies();
            if (number != 0) {
                if (UserActions.verifyDoAction == 1) {
                    UserActions.currentUser.setNumFreePremiumMovies(number - 1);
                    Input.getInstance().getUsers().get(UserActions.positionUser).
                            setNumFreePremiumMovies(number - 1);

                    Input.getInstance().getUsers().get(UserActions.positionUser).
                            getPurchasedMovies().add(UserActions.filteredMovieList.get(0));
                    System.out.println("am intrat aici de mai multe ori iar size array e " + UserActions.filteredMovieList.size());
                    UserActions.filteredMovieList.get(0).setOkPurchase(1);
                    Input.getInstance().getMovies().get(UserActions.positionMovie).setOkPurchase(1);
                    System.out.println("am iesit pe purchase");
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
            System.out.println("nu e bn la purchase 2 cond");
            return null;
        }
        UserActions.filteredMovieList.get(0).setOkPurchase(1);
        Input.getInstance().getMovies().get(UserActions.positionMovie).setOkPurchase(1);
        System.out.println("am iesit ok pe purchase");
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    public ActionChangePageAndOnPage watch(Action action) {
        System.out.println("am intrat pe watch");
        if (!action.getMovie().equals(UserActions.filteredMovieList.get(0).getName())) {
            System.out.println("nu e bn la watch 1 cond");
            return null;
        }
        if (UserActions.filteredMovieList.get(0).getOkPurchase() == 0) {
            System.out.println("nu e bn pe watch 2 cond");
            return null;
        }
        if (UserActions.verifyDoAction == 1) {
            Input.getInstance().getUsers().get(UserActions.positionUser).getWatchedMovies().
                    add(UserActions.filteredMovieList.get(0));
            UserActions.filteredMovieList.get(0).setOkWatch(1);
            Input.getInstance().getMovies().get(UserActions.positionMovie).setOkWatch(1);
            System.out.println("am iesit pe watch");
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    public ActionChangePageAndOnPage like(Action action) {
        System.out.println("am intrat pe like");
        if (!action.getMovie().equals(UserActions.filteredMovieList.get(0).getName())) {
            System.out.println("nu e bine pe like 1 cond");
            return null;
        }
        if (UserActions.filteredMovieList.get(0).getOkWatch() == 0) {
            System.out.println("nu e bn pe like 2 cond");
            return null;
        }
        if (UserActions.verifyDoAction == 1) {
            System.out.println("sunt aici");
            System.out.println(UserActions.positionMovie);
            System.out.println(Input.getInstance().getMovies().size());
            int like = Input.getInstance().getMovies().get(UserActions.positionMovie).getNumLikes() + 1;
            System.out.println("pfiu am scapat");
            UserActions.filteredMovieList.get(0).setNumLikes(like);
            Input.getInstance().getMovies().get(UserActions.positionMovie).setNumLikes(like);
            Input.getInstance().getUsers().get(UserActions.positionUser).getLikedMovies().
                    add(UserActions.filteredMovieList.get(0));
            System.out.println("am iesit pe like");
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }

    public ActionChangePageAndOnPage rateMovie(Action action) {
        if (!action.getMovie().equals(UserActions.filteredMovieList.get(0).getName())) {
            return null;
        }
        if (UserActions.filteredMovieList.get(0).getOkWatch() == 0 || action.getRate() > MAXRATING) {
            return null;
        }

        if (UserActions.verifyDoAction == 1) {
            Input.getInstance().getUsers().get(UserActions.positionUser).getRatedMovies().
                    add(UserActions.filteredMovieList.get(0));

            Input.getInstance().getMovies().get(UserActions.positionMovie).getRatingList().
                    add(action.getRate());
            ArrayList<Double> ratings = Input.getInstance().getMovies().
                    get(UserActions.positionMovie).getRatingList();

            double finalRating = 0.00;
            for (Double rate : ratings) {
                finalRating += rate;
            }
            finalRating /= ratings.size();

            UserActions.filteredMovieList.get(0).setRating(finalRating);
            Input.getInstance().getMovies().get(UserActions.positionMovie).setRating(finalRating);
        }

        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.SeeDetails);
    }
}
