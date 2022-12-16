package OutputData;

import InputData.MovieList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class ArrayNodeList {
    public static ArrayNode movieArray(ArrayList<MovieList> movieList, ObjectMapper mapper) {
        ArrayNode movieArrayNode = mapper.createArrayNode();

        for (MovieList movie : movieList) {
            ObjectNode movieNode = mapper.createObjectNode();

            movieNode.put("name", movie.getName());
            movieNode.put("year", movie.getYear());
            movieNode.put("duration", movie.getDuration());
            movieNode.putPOJO("genres", new ArrayList<>(movie.getGenres()));
            movieNode.putPOJO("actors", new ArrayList<>(movie.getActors()));
            movieNode.putPOJO("countriesBanned", new ArrayList<>(movie.getCountriesBanned()));
            movieNode.put("numLikes", movie.getNumLikes());
            movieNode.put("rating", movie.getRating());
            movieNode.put("numRatings", movie.getNumRatings());
            movieArrayNode.add(movieNode);
        }
        return movieArrayNode;
    }
}
