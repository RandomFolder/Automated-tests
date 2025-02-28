package requests;

import java.util.Map;

import constants.SharedConstants;
import io.restassured.response.Response;
import task4.utils.HttpRequests;

public class TrashContentRequest {
    private static final String CONTENT = "/disk/trash/resources";

    public static Response getMetadata(Map<String, String> parameters) {
        return HttpRequests.getRequest(SharedConstants.HEADERS, SharedConstants.BASE_URI, CONTENT, parameters);
    }
}
