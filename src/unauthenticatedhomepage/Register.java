package unauthenticatedhomepage;

import actions.ActionChangePageAndOnPage;
import actions.FactoryChangePageAndOnPage;
import actions.UserActions;
import inputdata.Action;
import inputdata.AllFeaturesEnum;
import inputdata.AllPagesEnum;
import inputdata.Input;
import inputdata.User;

public class Register extends ActionChangePageAndOnPage {
    public Register() {
        this.nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Register);
        this.features.put(AllFeaturesEnum.Register, (action) -> register(action));
    }

    /**
     * This function inserts the credentials from the action in the user array from the input, then
     * it logs in the person
     * @param action - where the credentials are
     * @return - the state of the authenticated page
     */
    public ActionChangePageAndOnPage register(final Action action) {
        Input listUsers = Input.getInstance();
        User addUser = new User();
        UserActions.positionUser = listUsers.getUsers().size();
        addUser.setCredentials(action.getCredentials());
        UserActions.currentUser = addUser;
        listUsers.getUsers().add(addUser);
        return FactoryChangePageAndOnPage.getInstance().
                getState(AllPagesEnum.AuthenticatedHomePage);
    }
}
