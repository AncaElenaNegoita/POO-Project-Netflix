package authenticatedhomepage;

import actions.ActionChangePageAndOnPage;
import inputdata.AllPagesEnum;

public class Logout extends ActionChangePageAndOnPage {
    public Logout() {
        this.nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Logout);
    }
}
