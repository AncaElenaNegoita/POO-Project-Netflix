import actions.UserActions;
import inputdata.Input;
import inputdata.User;
import unauthenticatedhomepage.UnauthenticatedHomePage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    /**
     * Main creates the instance of input, reading the value from the file using Object Mapper
     * and then calls the function from UserActions. The output is put in the file output
     * using an Object Writer and the function writeValue
     * @param args - the first 2 elements contain the file input and the file output
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input.setInstance(objectMapper.readValue(new File(args[0]), Input.class));
        Input inputData = Input.getInstance();

        ArrayNode output = objectMapper.createArrayNode();

        UserActions action = new UserActions();
        UserActions.currentPage = new UnauthenticatedHomePage();
        UserActions.verifyDoAction = 0;
        UserActions.currentUser = new User();
        UserActions.filteredMovieList = new ArrayList<>();
        UserActions.positionUser = 0;
        UserActions.positionMovie = 0;
        action.actions(inputData, output);

        Input.resetInstance(null);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
