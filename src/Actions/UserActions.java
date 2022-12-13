package Actions;

import InputData.Action;
import InputData.Input;
import InputData.MovieList;
import InputData.User;
import OutputData.NodeChangePage;
import UnauthenticatedHomePage.UnauthenticatedHomePage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class UserActions {
    private ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    public static ActionChangePageAndOnPage currentPage = new UnauthenticatedHomePage();
    public static User currentUser = new User();
    public static ArrayList<MovieList> filteredMovieList = new ArrayList<>();

    public void actions(final Input input, final ArrayNode output) {
        for (Action action : input.getActions()) {
            ObjectNode node = mapper.createObjectNode();

            if (action.getType().equals("change page")) {
                if (currentPage.changePage(action) == null) {
                    node = NodeChangePage.changePageError(node);
                } else {
                    node = NodeChangePage.changePageSuccessful(node, action);
                    currentPage = currentPage.changePage(action);
                }
            } else if (action.getType().equals("on page")) {
                if (currentPage.onPage(action) == null) {
                    node = NodeChangePage.changePageError(node);
                } else {
                    node = NodeChangePage.onPageOutput(node, action);
                    currentPage = currentPage.onPage(action);
                }
            }
            if (!node.isEmpty()) {
                output.add(node);
            }
        }
    }
}
