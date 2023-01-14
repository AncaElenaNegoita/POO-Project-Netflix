package actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import inputdata.Action;
import inputdata.Input;
import inputdata.MovieList;
import inputdata.User;
import outputdata.NodeChangePage;
import unauthenticatedhomepage.UnauthenticatedHomePage;

import java.util.ArrayList;

/**
 * This class goes through the array of actions and verifies which action should be performed.
 */
public class UserActions {
    private final ObjectMapper mapper = new ObjectMapper().
            enable(SerializationFeature.INDENT_OUTPUT);
    public static ActionChangePageAndOnPage currentPage = new UnauthenticatedHomePage();
    public static int verifyDoAction = 0;
    public static User currentUser = new User();
    public static ArrayList<MovieList> filteredMovieList = new ArrayList<>();
    public static int positionUser;
    public static int positionMovie;
    public static ArrayList<String> nameOfPages = new ArrayList<>();
    public static ArrayList<ActionChangePageAndOnPage> stackOfPages = new ArrayList<>();
    public static Action seeDetailAction = new Action();

    /**
     * The specific function is called from ActionChangePageAndOnPage and the result is verified.
     * If the function return null, then an error will be added in the output, else, the current
     * page becomes the one returned from the function and an output is created, if necessary.
     * @param input - what is given in the input file is put in 3 arrays
     * @param output - what the output file will show
     */
    public void actions(final Input input, final ArrayNode output) {
        for (int j = 0; j < Input.getInstance().getMovies().size(); j++) {
            for (int i = 0; i < Input.getInstance().getUsers().size(); i++) {
                Input.getInstance().getMovies().get(j).getOkPurchase().add(0);
                Input.getInstance().getMovies().get(j).getOkWatch().add(0);
                Input.getInstance().getMovies().get(j).getOkLike().add(0);
                Input.getInstance().getMovies().get(j).getOkRate().add(0);
                Input.getInstance().getMovies().get(j).getRatingList().add(0.00);
            }
        }
        for (Action action : input.getActions()) {
            ObjectNode node = mapper.createObjectNode();

            switch (action.getType()) {
                case "change page":
                    if (currentPage.changePage(action, action.getPage()) == null) {
                        node = NodeChangePage.changePageError(node);
                    } else {
                        verifyDoAction = 1;
                        currentPage = currentPage.changePage(action, action.getPage());
                        if (!(action.getPage().equals("login")) && !(action.getPage().
                                equals("register")) && !(action.getPage().equals("logout"))) {
                            nameOfPages.add(action.getPage());
                            stackOfPages.add(currentPage);
                        }

                        if (!(action.getPage().equals("login")) && !(action.getPage().
                                equals("register"))) {
                            node = NodeChangePage.changePageSuccessful(node,
                                    action.getPage());
                        }
                    }
                    break;
                case "on page":
                    if (currentPage.onPage(action) == null) {
                        node = NodeChangePage.changePageError(node);
                    } else {
                        verifyDoAction = 1;
                        currentPage = currentPage.onPage(action);
                        if (!action.getFeature().equals("buy tokens")
                                && !action.getFeature().equals("buy premium account")
                                && !action.getFeature().equals("subscribe")) {
                            node = NodeChangePage.onPageOutput(node, mapper);
                        }
                    }
                    break;
                case "back":
                    if (stackOfPages.size() <= 1) {
                        node = NodeChangePage.changePageError(node);
                    } else {
                        stackOfPages.remove(stackOfPages.size() - 1);
                        nameOfPages.remove(nameOfPages.size() - 1);
                        currentPage = stackOfPages.get(stackOfPages.size() - 1);
                        if (!nameOfPages.get(nameOfPages.size() - 1).
                                equals("authenticatedHomePage")) {
                            if (nameOfPages.get(nameOfPages.size() - 1).equals("see details")) {
                                currentPage = currentPage.changePage(seeDetailAction, nameOfPages.
                                        get(nameOfPages.size() - 1));
                            } else {
                                currentPage = currentPage.changePage(action, nameOfPages.
                                        get(nameOfPages.size() - 1));
                            }
                            node = NodeChangePage.changePageSuccessful(node, nameOfPages.
                                    get(nameOfPages.size() - 1));
                        }
                    }
                    break;
                case "database":
                    if (action.getFeature().equals("add")) {
                        node = Database.databaseAdd(action, node);
                    } else if (action.getFeature().equals("delete")) {
                        node = Database.databaseDelete(action, node);
                    }
                    break;
                default:
                    break;
            }

            verifyDoAction = 0;
            if (!node.isEmpty()) {
                output.add(node);
            }
        }
        ObjectNode node = mapper.createObjectNode();
        node = Recommendation.getRecommendation(node);
        if (!node.isEmpty()) {
            output.add(node);
        }
        nameOfPages.clear();
        stackOfPages.clear();
    }
}
