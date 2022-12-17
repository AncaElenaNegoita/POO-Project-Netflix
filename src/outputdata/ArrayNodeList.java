package outputdata;

import inputdata.MovieList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class ArrayNodeList {
    /**
     * This function puts each field from a movie array into an array of nodes
     * @param movieList - the movie list that needs to be printed in the output
     * @param mapper
     * @return - the array of nodes that contain the movies
     */
    public static ArrayNode movieArray(final ArrayList<MovieList> movieList,
                                       final ObjectMapper mapper) {
        ArrayNode movieArrayNode = mapper.createArrayNode();

        for (MovieList movie : movieList) {
            ObjectNode movieNode = mapper.createObjectNode();

            movieNode.put("name", movie.getName());
            movieNode.put("year", movie.getYear());
            movieNode.put("duration", movie.getDuration());
            movieNode.putPOJO("genres", new ArrayList<>(movie.getGenres()));
            movieNode.putPOJO("actors", new ArrayList<>(movie.getActors()));
            movieNode.putPOJO("countriesBanned", new
                    ArrayList<>(movie.getCountriesBanned()));
            movieNode.put("numLikes", movie.getNumLikes());
            movieNode.put("rating", movie.getRating());
            movieNode.put("numRatings", movie.getNumRatings());
            movieArrayNode.add(movieNode);
        }
        return movieArrayNode;
    }
}
