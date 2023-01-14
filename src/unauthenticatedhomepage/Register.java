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

        if (UserActions.verifyDoAction == 1) {
        addUser.setCredentials(action.getCredentials());
        listUsers.getUsers().add(addUser);
        UserActions.positionUser = listUsers.getUsers().size() - 1;
        UserActions.currentUser = addUser;
            for (int j = 0; j < Input.getInstance().getMovies().size(); j++) {
                Input.getInstance().getMovies().get(j).getOkPurchase().add(0);
                Input.getInstance().getMovies().get(j).getOkWatch().add(0);
                Input.getInstance().getMovies().get(j).getOkLike().add(0);
                Input.getInstance().getMovies().get(j).getOkRate().add(0);
                Input.getInstance().getMovies().get(j).getRatingList().add(0.00);
            }
            UserActions.nameOfPages.add("authenticatedHomePage");
            UserActions.stackOfPages.add(FactoryChangePageAndOnPage.getInstance().
                    getState(AllPagesEnum.AuthenticatedHomePage));
        }
        return FactoryChangePageAndOnPage.getInstance().
                getState(AllPagesEnum.AuthenticatedHomePage);
    }
}
