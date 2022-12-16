package InputData;

import java.util.HashMap;
import java.util.Map;


public class Action {
    private String type;
    private String page;
    private String feature;
    private Filters filters;
    private String startsWith;
    private Credentials credentials;
    private String movie;
    private String count;
    private double rate;
    private static Map<String, AllPagesEnum> pageEnum = new HashMap<>() {{
        put("unauthenticated homepage", AllPagesEnum.UnauthenticatedHomePage);
        put("login", AllPagesEnum.Login);
        put("register", AllPagesEnum.Register);
        put("authenticated homepage", AllPagesEnum.AuthenticatedHomePage);
        put("movies", AllPagesEnum.Movies);
        put("see details", AllPagesEnum.SeeDetails);
        put("upgrades", AllPagesEnum.Upgrades);
        put("logout", AllPagesEnum.Logout);
    }};

    private Map<String, AllFeaturesEnum> featureEnum = new HashMap<String, AllFeaturesEnum>() {{
        put("login", AllFeaturesEnum.Login);
        put("register", AllFeaturesEnum.Register);
        put("search", AllFeaturesEnum.Search);
        put("filter", AllFeaturesEnum.Filter);
        put("purchase", AllFeaturesEnum.Purchase);
        put("watch", AllFeaturesEnum.Watch);
        put("like", AllFeaturesEnum.Like);
        put("rate", AllFeaturesEnum.RateTheMovie);
        put("buy tokens", AllFeaturesEnum.BuyTokens);
        put("buy premium account", AllFeaturesEnum.BuyPremiumAccount);
        put("logout", AllFeaturesEnum.Logout);
    }};

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public static Map<String, AllPagesEnum> getPageEnum() {
        return pageEnum;
    }

    public static void setPageEnum(Map<String, AllPagesEnum> pageEnum) {
        Action.pageEnum = pageEnum;
    }

    public Map<String, AllFeaturesEnum> getFeatureEnum() {
        return featureEnum;
    }

    public void setFeatureEnum(Map<String, AllFeaturesEnum> featureEnum) {
        this.featureEnum = featureEnum;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
