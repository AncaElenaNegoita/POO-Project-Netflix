package UnauthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import Actions.FactoryChangePageAndOnPage;
import Actions.UserActions;
import InputData.*;

import java.util.ArrayList;

public class Login extends ActionChangePageAndOnPage {
    public Login() {
        this.nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Login);
        this.features.put(AllFeaturesEnum.Login, (action) -> login(action));
    }

    public ActionChangePageAndOnPage login(Action action) {
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
//        for (User user: listUsers.getUsers()) {
//            if (user.getCredentials().equals(action.getCredentials())) {
//                UserActions.currentUser = user;
//                ok = 1;
//                break;
//            }
//        }
        if (ok == 1) {
            System.out.println(Input.getInstance().getUsers().get(UserActions.positionUser).getCredentials().getBalance());
            return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.AuthenticatedHomePage);
        } else {
            UserActions.currentPage = FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.UnauthenticatedHomePage);
            return null;
        }
    }
}
