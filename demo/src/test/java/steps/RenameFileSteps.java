package steps;

import java.util.Map;
import io.restassured.response.Response;
import requests.MoveRequest;

public class RenameFileSteps {
    public static Response renameFile(Map<String, String> parameters) {
        return MoveRequest.moveFile(parameters);
    }
}
