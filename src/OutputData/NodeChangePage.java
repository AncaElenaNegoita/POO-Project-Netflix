package OutputData;

import Actions.UserActions;
import InputData.Action;
import InputData.MovieList;
import InputData.User;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

import static Actions.UserActions.currentUser;

public class NodeChangePage {
    public static ObjectNode changePageError(ObjectNode node) {
        node.put("error", "Error");
        node.putPOJO("currentMoviesList", new ArrayList<>(currentUser.getUserMovies()));
        node.putPOJO("currentUser", null);
        return node;
    }

    public static ObjectNode changePageSuccessful(ObjectNode node, Action action) {
        if (action.getPage().equals("movies")) {
            node.putPOJO("error", null);
            node.putPOJO("currentMoviesList", new ArrayList<>(UserActions.filteredMovieList));
            node.putPOJO("currentUser", new User(currentUser));
        }

        return node;
    }

    public static ObjectNode onPageOutput(ObjectNode node, Action action) {
        node.putPOJO("error", null);
        node.putPOJO("currentMoviesList", new ArrayList<MovieList>(UserActions.filteredMovieList));
        node.putPOJO("currentUser", new User(currentUser));
        return node;
    }
}
