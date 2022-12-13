package AuthenticatedHomePage;

import Actions.ActionChangePageAndOnPage;
import InputData.AllPagesEnum;

public class AuthenticatedHomePage extends ActionChangePageAndOnPage {
    public AuthenticatedHomePage() {
        this.nextStates.add(AllPagesEnum.AuthenticatedHomePage);
        this.nextStates.add(AllPagesEnum.Movies);
        this.nextStates.add(AllPagesEnum.Updgrades);
        this.nextStates.add(AllPagesEnum.Logout);
    }
}
