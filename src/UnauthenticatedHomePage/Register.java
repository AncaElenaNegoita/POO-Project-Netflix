package UnauthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import Actions.FactoryChangePageAndOnPage;
import Actions.UserActions;
import InputData.*;

public class Register extends ActionChangePageAndOnPage {
    public Register() {
        this.nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Register);
        this.features.put(AllFeaturesEnum.Register, (action) -> register(action));
    }

    public ActionChangePageAndOnPage register(Action action) {
        Input listUsers = Input.getInstance();
        User addUser = new User();
        UserActions.positionUser = listUsers.getUsers().size();
        addUser.setCredentials(action.getCredentials());
        UserActions.currentUser = addUser;
        listUsers.getUsers().add(addUser);
        return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.AuthenticatedHomePage);
    }
}
