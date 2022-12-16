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
    public static int verifyDoAction = 0;
    public static User currentUser = new User();
    public static ArrayList<MovieList> filteredMovieList = new ArrayList<>();
    public static int positionUser;
    public static int positionMovie;

    public void actions(final Input input, final ArrayNode output) {
        for (Action action : input.getActions()) {
            ObjectNode node = mapper.createObjectNode();

            if (action.getType().equals("change page")) {
                if (currentPage.changePage(action) == null) {
                    System.out.println("error");
                    node = NodeChangePage.changePageError(node);
                } else {
                    verifyDoAction = 1;
                    currentPage = currentPage.changePage(action);
                    if  (!(action.getPage().equals("login")) && !(action.getPage().equals("register"))) {
                        System.out.println("sunt la change page in main ok");
                        node = NodeChangePage.changePageSuccessful(node, action, mapper);
                        System.out.println(node);
                    }
                }
            } else if (action.getType().equals("on page")) {
                if (currentPage.onPage(action) == null) {
                    System.out.println("error");
                    node = NodeChangePage.changePageError(node);
                } else {

                        verifyDoAction = 1;
                        currentPage = currentPage.onPage(action);
                    if (!action.getFeature().equals("buy tokens") &&
                            !action.getFeature().equals("buy premium account")) {
                        node = NodeChangePage.onPageOutput(node, action, mapper);
                        System.out.println(node);
                    }
                }
            }
            verifyDoAction = 0;
            System.out.println(currentPage);
            if (!node.isEmpty()) {
                if (!filteredMovieList.isEmpty()) {
                    System.out.println(Input.getInstance().getMovies().get(positionMovie).getOkPurchase());
                    System.out.println(filteredMovieList.get(0).getOkPurchase());
                }
                System.out.println(node);
                output.add(node);
                System.out.println(output);
            }
        }
    }
}
