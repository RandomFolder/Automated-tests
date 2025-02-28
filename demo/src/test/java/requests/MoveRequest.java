package requests;

import java.util.Map;

import constants.SharedConstants;
import io.restassured.response.Response;
import task4.utils.HttpRequests;

public class MoveRequest {
    private static final String MOVE = "/disk/resources/move";

    public static Response moveFile(Map<String, String> parameters) {
        return HttpRequests.postRequest(SharedConstants.HEADERS, SharedConstants.BASE_URI, MOVE, parameters);
    }
}
