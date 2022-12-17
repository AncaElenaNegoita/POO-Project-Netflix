package outputdata;

import actions.UserActions;
import inputdata.Action;
import inputdata.Input;
import inputdata.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class NodeChangePage {
    /**
     * This function puts the errors in a node
     * @param node - the node that will have the errors and will be added to the output
     * @return - the node
     */
    public static ObjectNode changePageError(final ObjectNode node) {
        node.put("error", "Error");
        node.putPOJO("currentMoviesList", new ArrayList<>());
        node.putPOJO("currentUser", null);
        return node;
    }

    /**
     * This function puts the current user and its movie list in a node when the current page is
     * movies or see details(for change page)
     * @param node - the node that will have the errors and will be added to the output
     * @return - the node
     */
    public static ObjectNode changePageSuccessful(final ObjectNode node, final Action action,
                                                  final ObjectMapper mapper) {
        User user = Input.getInstance().getUsers().get(UserActions.positionUser);
        if (action.getPage().equals("movies") || action.getPage().equals("see details")) {
            node.putPOJO("error", null);

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode movies = ArrayNodeList.movieArray(UserActions.filteredMovieList,
                    objectMapper);

            node.put("currentMoviesList", movies);
            ObjectNode userNode = mapper.createObjectNode();

            ObjectNode credNode = mapper.createObjectNode();
            credNode.put("name", user.getCredentials().getName());
            credNode.put("password", user.getCredentials().getPassword());
            credNode.put("accountType", user.getCredentials().getAccountType());
            credNode.put("country", user.getCredentials().getCountry());
            credNode.put("balance", user.getCredentials().getBalance());

            userNode.put("credentials", credNode);
            userNode.put("tokensCount", user.getTokensCount());
            userNode.put("numFreePremiumMovies", user.getNumFreePremiumMovies());

            objectMapper = new ObjectMapper();
            ArrayNode purchasedMovies = ArrayNodeList.movieArray(user.getPurchasedMovies(),
                    objectMapper);
            userNode.put("purchasedMovies", purchasedMovies);

            objectMapper = new ObjectMapper();
            ArrayNode watchedMovies = ArrayNodeList.movieArray(user.getWatchedMovies(),
                    objectMapper);
            userNode.put("watchedMovies", watchedMovies);

            objectMapper = new ObjectMapper();
            ArrayNode likedMovies = ArrayNodeList.movieArray(user.getLikedMovies(),
                    objectMapper);
            userNode.put("likedMovies", likedMovies);

            objectMapper = new ObjectMapper();
            ArrayNode ratedMovies = ArrayNodeList.movieArray(user.getRatedMovies(),
                    objectMapper);
            userNode.put("ratedMovies", ratedMovies);
            node.put("currentUser", userNode);
        }

        return node;
    }

    /**
     * This function puts the current user and its movie list in a node whenever a feature is
     * applied, and it is successful(for change page)
     * @param node - the node that will have the errors and will be added to the output
     * @return - the node
     */
    public static ObjectNode onPageOutput(final ObjectNode node, final ObjectMapper mapper) {
        User user = new User(Input.getInstance().getUsers().get(UserActions.positionUser));
        node.putPOJO("error", null);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode movies = ArrayNodeList.movieArray(UserActions.filteredMovieList, objectMapper);

        node.put("currentMoviesList", movies);
        ObjectNode userNode = mapper.createObjectNode();

        ObjectNode credNode = mapper.createObjectNode();
        credNode.put("name", user.getCredentials().getName());
        credNode.put("password", user.getCredentials().getPassword());
        credNode.put("accountType", user.getCredentials().getAccountType());
        credNode.put("country", user.getCredentials().getCountry());
        credNode.put("balance", user.getCredentials().getBalance());

        userNode.put("credentials", credNode);
        userNode.put("tokensCount", user.getTokensCount());
        userNode.put("numFreePremiumMovies", user.getNumFreePremiumMovies());

        objectMapper = new ObjectMapper();

        ArrayNode purchasedMovies = ArrayNodeList.movieArray(user.getPurchasedMovies(),
                objectMapper);
        userNode.put("purchasedMovies", purchasedMovies);

        objectMapper = new ObjectMapper();
        ArrayNode watchedMovies = ArrayNodeList.movieArray(user.getWatchedMovies(),
                objectMapper);
        userNode.put("watchedMovies", watchedMovies);

        objectMapper = new ObjectMapper();
        ArrayNode likedMovies = ArrayNodeList.movieArray(user.getLikedMovies(),
                objectMapper);
        userNode.put("likedMovies", likedMovies);

        objectMapper = new ObjectMapper();
        ArrayNode ratedMovies = ArrayNodeList.movieArray(user.getRatedMovies(),
                objectMapper);
        userNode.put("ratedMovies", ratedMovies);
        node.put("currentUser", userNode);

        return node;
    }
}
