package AuthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import InputData.AllPagesEnum;

public class Logout extends ActionChangePageAndOnPage {
    public Logout() {
        this.nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Logout);
    }
}
