package authenticatedhomepage;

import actions.ActionChangePageAndOnPage;
import actions.FactoryChangePageAndOnPage;
import actions.UserActions;
import inputdata.Action;
import inputdata.AllFeaturesEnum;
import inputdata.AllPagesEnum;
import inputdata.Input;

public class Upgrades extends ActionChangePageAndOnPage {
    static final int PREMIUMTOKENS = 10;
    public Upgrades() {
        this.nextStates.add(AllPagesEnum.AuthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Movies);
        this.nextStates.add(AllPagesEnum.Upgrades);
        this.nextStates.add(AllPagesEnum.Logout);
        this.features.put(AllFeaturesEnum.BuyPremiumAccount, this::buyPremiumAccount);
        this.features.put(AllFeaturesEnum.BuyTokens, this::buyTokens);
    }

    /**
     * This function lets a user purchase a premium account using tokens
     * @param action
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage buyPremiumAccount(final Action action) {
        if (UserActions.currentUser.getTokensCount() < PREMIUMTOKENS) {
            return null;
        }

        if (UserActions.verifyDoAction == 1) {
            UserActions.currentUser.getCredentials().setAccountType("premium");
            Input.getInstance().getUsers().get(UserActions.positionUser).getCredentials().
                    setAccountType("premium");
            int token = UserActions.currentUser.getTokensCount();
            UserActions.currentUser.setTokensCount(token - PREMIUMTOKENS);
            Input.getInstance().getUsers().get(UserActions.positionUser).
                    setTokensCount(token - PREMIUMTOKENS);
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Upgrades);
    }

    /**
     * This function lets a user purchase tokens in order to use them on the app
     * @param action
     * @return - the state of the current page
     */
    public ActionChangePageAndOnPage buyTokens(final Action action) {
        int balance = Integer.parseInt(UserActions.currentUser.getCredentials().getBalance());
        int numberTokens = Integer.parseInt(action.getCount());
        if (balance < numberTokens) {
            return null;
        }
        if (UserActions.verifyDoAction == 1) {
            UserActions.currentUser.setTokensCount(UserActions.currentUser.getTokensCount()
                    + numberTokens);
            balance -= numberTokens;
            UserActions.currentUser.getCredentials().setBalance(String.valueOf(balance));

            Input.getInstance().getUsers().get(UserActions.positionUser).
                    setTokensCount(UserActions.currentUser.getTokensCount());
            Input.getInstance().getUsers().get(UserActions.positionUser).getCredentials().
                    setBalance(String.valueOf(balance));
        }

        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Upgrades);
    }
}
