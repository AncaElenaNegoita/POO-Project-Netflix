package UnauthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import InputData.AllPagesEnum;

public class UnauthenticatedHomePage extends ActionChangePageAndOnPage {
    public UnauthenticatedHomePage() {
        nextStates.add(AllPagesEnum.UnauthenticatedHomePage);
        nextStates.add(AllPagesEnum.Login);
        nextStates.add(AllPagesEnum.Register);
    }
}
