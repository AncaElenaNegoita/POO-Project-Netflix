package UnauthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import Actions.FactoryChangePageAndOnPage;
import Actions.UserActions;
import InputData.*;

public class Login extends ActionChangePageAndOnPage {
    public Login() {
        this.nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Login);
        this.features.put(AllFeaturesEnum.Login, (action) -> login(action));
    }

    public ActionChangePageAndOnPage login(Action action) {
        int ok = 0;
        Input listUsers = Input.getInstance();
        for (User user: listUsers.getUsers()) {
            if (user.getCredentials().equals(action.getCredentials())) {
                UserActions.currentUser = user;
                ok = 1;
                break;
            }
        }
        if (ok == 1) {
            return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.AuthenticatedHomePage);
        } else {
            UserActions.currentPage = FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.UnauthenticatedHomePage);
            return null;
        }
    }
}
