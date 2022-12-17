package actions;

import inputdata.Action;
import inputdata.Input;
import inputdata.MovieList;
import inputdata.User;
import outputdata.NodeChangePage;
import unauthenticatedhomepage.UnauthenticatedHomePage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

/**
 * This class goes through the array of actions and verifies which action should be performed.
 */
public class UserActions {
    private ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    public static ActionChangePageAndOnPage currentPage = new UnauthenticatedHomePage();
    public static int verifyDoAction = 0;
    public static User currentUser = new User();
    public static ArrayList<MovieList> filteredMovieList = new ArrayList<>();
    public static int positionUser;
    public static int positionMovie;

    /**
     * The specific function is called from ActionChangePageAndOnPage and the result is verified.
     * If the function return null, then an error will be added in the output, else, the current
     * page becomes the one returned from the function and an output is created, if necessary.
     * @param input - what is given in the input file is put in 3 arrays
     * @param output - what the output file will show
     */
    public void actions(final Input input, final ArrayNode output) {
        for (Action action : input.getActions()) {
            ObjectNode node = mapper.createObjectNode();

            if (action.getType().equals("change page")) {
                if (currentPage.changePage(action) == null) {
                    node = NodeChangePage.changePageError(node);
                } else {
                    verifyDoAction = 1;
                    currentPage = currentPage.changePage(action);
                    if  (!(action.getPage().equals("login")) && !(action.getPage().
                            equals("register"))) {
                        node = NodeChangePage.changePageSuccessful(node, action, mapper);
                    }
                }
            } else if (action.getType().equals("on page")) {
                if (currentPage.onPage(action) == null) {
                    node = NodeChangePage.changePageError(node);
                } else {

                        verifyDoAction = 1;
                        currentPage = currentPage.onPage(action);
                    if (!action.getFeature().equals("buy tokens")
                            && !action.getFeature().equals("buy premium account")) {
                        node = NodeChangePage.onPageOutput(node, mapper);
                    }
                }
            }
            verifyDoAction = 0;
            if (!node.isEmpty()) {
                output.add(node);
            }
        }
    }
}
