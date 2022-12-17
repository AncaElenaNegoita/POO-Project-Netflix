package actions;

import authenticatedhomepage.*;
import inputdata.AllPagesEnum;
import unauthenticatedhomepage.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This function creates an instance for each individual page(class) using Factory Design Pattern
 * and with Singleton they are only created once and are accessible from all the other classes.
 * All the pages from AllPagesEnum and the instances of the classes with the same name as the
 * pages are associated in a map.
 */
public final class FactoryChangePageAndOnPage {
    // Factory Design Pattern
    private static FactoryChangePageAndOnPage instance = null;

    private interface CreateStateInstance {
        ActionChangePageAndOnPage createStateInstance();
    }
    private Map<AllPagesEnum, CreateStateInstance> stateCreators;

    private FactoryChangePageAndOnPage() {
        stateCreators = new HashMap<>();
        stateCreators.put(AllPagesEnum.UnauthenticatedHomePage,
                () -> new UnauthenticatedHomePage());
        stateCreators.put(AllPagesEnum.Login, () -> new Login());
        stateCreators.put(AllPagesEnum.Register, () -> new Register());
        stateCreators.put(AllPagesEnum.AuthenticatedHomePage, () -> new AuthenticatedHomePage());
        stateCreators.put(AllPagesEnum.Movies, () -> new Movies());
        stateCreators.put(AllPagesEnum.SeeDetails, () -> new SeeDetails());
        stateCreators.put(AllPagesEnum.Upgrades, () -> new Upgrades());
        stateCreators.put(AllPagesEnum.Logout, () -> new Logout());
    }

    // Singleton Design Pattern

    /**
     * The function creates the map that have a key represented by the page in AllPagesEnum, and a
     * function declared in the interface that creates a new instance of the class(that is
     * represented by the page, the class has the same name as the page)
     * @return - the instance of the class
     */
    public static FactoryChangePageAndOnPage getInstance() {
        if (instance == null) {
            instance = new FactoryChangePageAndOnPage();
        }

        return instance;
    }

    /**
     *
     * @param page - the page from pageEnum that helps the function return the state of the next
     *             of the needed page
     * @return - the state of the next page(that becomes the current one)
     */
    public ActionChangePageAndOnPage getState(final AllPagesEnum page) {
        return stateCreators.get(page).createStateInstance();
    }
}
