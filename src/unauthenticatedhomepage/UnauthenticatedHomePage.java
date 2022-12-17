package unauthenticatedhomepage;

import actions.ActionChangePageAndOnPage;
import inputdata.AllPagesEnum;

public class UnauthenticatedHomePage extends ActionChangePageAndOnPage {
    public UnauthenticatedHomePage() {
        nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        nextStates.add(AllPagesEnum.Login);
        nextStates.add(AllPagesEnum.Register);
    }
}
