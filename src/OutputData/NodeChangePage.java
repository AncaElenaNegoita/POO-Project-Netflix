package OutputData;

import Actions.UserActions;
import InputData.Action;
import InputData.Input;
import InputData.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class NodeChangePage {
    public static ObjectNode changePageError(ObjectNode node) {
        node.put("error", "Error");
        node.putPOJO("currentMoviesList", new ArrayList<>());
        node.putPOJO("currentUser", null);
        return node;
    }

    public static ObjectNode changePageSuccessful(ObjectNode node, Action action, ObjectMapper mapper) {
        User user = Input.getInstance().getUsers().get(UserActions.positionUser);
        if (action.getPage().equals("movies") || action.getPage().equals("see details")) {
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
        }

        return node;
    }

    public static ObjectNode onPageOutput(ObjectNode node, Action action, ObjectMapper mapper) {
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
