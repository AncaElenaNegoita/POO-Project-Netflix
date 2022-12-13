package Actions;

import InputData.*;

import java.util.*;

public abstract class ActionChangePageAndOnPage {
    // State Design Pattern
    protected interface ApplyFeature {
        ActionChangePageAndOnPage applyFeature(Action action);
    }

    protected Collection<AllPagesEnum> nextStates = new HashSet<>();
    protected Map<AllFeaturesEnum, ApplyFeature> features = new HashMap<>();

    public ActionChangePageAndOnPage changePage(Action action) {
        String page = action.getPage();
        if (page.equals("logout")) {
            UserActions.currentUser = new User();
            UserActions.filteredMovieList = new ArrayList<>();
            CopyMovieList.getInstance().setOk(0);
            return FactoryChangePageAndOnPage.getInstance().getState(AllPagesEnum.UnauthenticatedHomePage);
        }
        if (page.equals("movies")) {
            UserActions.filteredMovieList = Actions.CopyMovieList.getInstance().getCopiedList(Input.getInstance().getMovies());
        }
        var nextPage = action.getPageEnum().get(page);
        if (nextStates.contains(nextPage)) {
            return FactoryChangePageAndOnPage.getInstance().getState(nextPage);
        }
        return null;
    }

    public ActionChangePageAndOnPage onPage(Action action) {
        String applyFeature = action.getFeature();
        var feature = action.getFeatureEnum().get(applyFeature);
        if (features.containsKey(feature)) {
            return features.get(feature).applyFeature(action);
        }
        return null;
    }
}
