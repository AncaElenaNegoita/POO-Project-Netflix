import Actions.UserActions;
import InputData.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        Input.setInstance(objectMapper.readValue(new File(args[0]), Input.class));
        Input inputData = Input.getInstance();

        ArrayNode output = objectMapper.createArrayNode();

        UserActions action = new UserActions();
        action.actions(inputData, output);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
