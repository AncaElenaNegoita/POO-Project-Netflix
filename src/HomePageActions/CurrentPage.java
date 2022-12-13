package HomePageActions;

public class CurrentPage {
    private static CurrentPage instance = null;
    private String currentPage;

    private CurrentPage () {}

    private CurrentPage (String currentPage) {
        this.currentPage = currentPage;
    }

    // Singleton Design Pattern

    public static CurrentPage getInstance(String currentPage) {
        if (instance == null) {
            instance = new CurrentPage(currentPage);
        }
        return instance;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }
}
