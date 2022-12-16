package AuthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import Actions.FactoryChangePageAndOnPage;
import Actions.UserActions;
import InputData.*;

public class Upgrades extends ActionChangePageAndOnPage {
    public Upgrades () {
        this.nextStates.add(AllPagesEnum.AuthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Movies);
        this.nextStates.add(AllPagesEnum.Upgrades);
        this.nextStates.add(AllPagesEnum.Logout);
        this.features.put(AllFeaturesEnum.BuyPremiumAccount, this::buyPremiumAccount);
        this.features.put(AllFeaturesEnum.BuyTokens, this::buyTokens);
    }

    public ActionChangePageAndOnPage buyPremiumAccount(Action action) {
        if (UserActions.currentUser.getTokensCount() < 10)
            return null;

        if (UserActions.verifyDoAction == 1) {
            UserActions.currentUser.getCredentials().setAccountType("premium");
            Input.getInstance().getUsers().get(UserActions.positionUser).getCredentials().
                    setAccountType("premium");
            int token = UserActions.currentUser.getTokensCount();
            UserActions.currentUser.setTokensCount(token - 10);
            Input.getInstance().getUsers().get(UserActions.positionUser).setTokensCount(token - 10);
        }
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Upgrades);
    }

    public ActionChangePageAndOnPage buyTokens(Action action) {
        int balance = Integer.parseInt(UserActions.currentUser.getCredentials().getBalance());
        System.out.println(balance);
        int numberTokens = Integer.parseInt(action.getCount());
        System.out.println(numberTokens);
        if (balance < numberTokens) {
            return null;
        }
        if (UserActions.verifyDoAction == 1) {
            UserActions.currentUser.setTokensCount(UserActions.currentUser.getTokensCount() + numberTokens);
            balance -= numberTokens;
            System.out.println(balance);
            UserActions.currentUser.getCredentials().setBalance(String.valueOf(balance));

            Input.getInstance().getUsers().get(UserActions.positionUser).
                    setTokensCount(UserActions.currentUser.getTokensCount());
            Input.getInstance().getUsers().get(UserActions.positionUser).getCredentials().
                    setBalance(String.valueOf(balance));
        }

        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.Upgrades);
    }
}
