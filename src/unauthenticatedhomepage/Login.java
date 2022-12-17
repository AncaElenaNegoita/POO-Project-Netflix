package unauthenticatedhomepage;

import actions.ActionChangePageAndOnPage;
import actions.FactoryChangePageAndOnPage;
import actions.UserActions;
import inputdata.Action;
import inputdata.AllFeaturesEnum;
import inputdata.AllPagesEnum;
import inputdata.Input;
import inputdata.User;

import java.util.ArrayList;

public class Login extends ActionChangePageAndOnPage {
    public Login() {
        this.nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Login);
        this.features.put(AllFeaturesEnum.Login, (action) -> login(action));
    }

    /**
     * This function verifies if the credentials from the action corespond to the ones in the
     * user array from the input
     * @param action - where the credentials are
     * @return - the state of the authenticated page if successful
     */
    public ActionChangePageAndOnPage login(final Action action) {
        int ok = 0;
        ArrayList<User> listUsers = Input.getInstance().getUsers();

        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getCredentials().equals(action.getCredentials())) {
                UserActions.currentUser = listUsers.get(i);
                UserActions.positionUser = i;
                ok = 1;
                break;
            }
        }
        if (ok == 1) {
            return FactoryChangePageAndOnPage.getInstance().
                    getState(AllPagesEnum.AuthenticatedHomePage);
        } else {
            UserActions.currentPage = FactoryChangePageAndOnPage.getInstance().
                    getState(AllPagesEnum.UnauthenticatedHomePage);
            return null;
        }
    }
}
