package Actions;

import AuthenticatedHomePage.*;
import InputData.AllPagesEnum;
import UnauthenticatedHomePage.*;

import java.util.HashMap;
import java.util.Map;

public final class FactoryChangePageAndOnPage {
    // Factory Design Pattern
    private static FactoryChangePageAndOnPage instance = null;

    private interface CreateStateInstance {
        ActionChangePageAndOnPage createStateInstance();
    }
    private Map<AllPagesEnum, CreateStateInstance> stateCreators;

    private FactoryChangePageAndOnPage() {
        stateCreators = new HashMap<>();
        stateCreators.put(AllPagesEnum.UnauthenticatedHomePage, () -> new UnauthenticatedHomePage());
        stateCreators.put(AllPagesEnum.Login, () -> new Login());
        stateCreators.put(AllPagesEnum.Register, () -> new Register());
        stateCreators.put(AllPagesEnum.AuthenticatedHomePage, () -> new AuthenticatedHomePage());
        stateCreators.put(AllPagesEnum.Movies, () -> new Movies());
        stateCreators.put(AllPagesEnum.SeeDetails, () -> new SeeDetails());
        stateCreators.put(AllPagesEnum.Upgrades, () -> new Upgrades());
        stateCreators.put(AllPagesEnum.Logout, () -> new Logout());
    }

    // Singleton Design Pattern
    public static FactoryChangePageAndOnPage getInstance() {
        if (instance == null) {
            instance = new FactoryChangePageAndOnPage();
        }

        return instance;
    }

    public ActionChangePageAndOnPage getState(AllPagesEnum page) {
        return stateCreators.get(page).createStateInstance();
    }
}
