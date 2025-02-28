package requests;

import java.util.Map;

import constants.SharedConstants;
import io.restassured.response.Response;
import task4.utils.HttpRequests;

public class DiskContentAndDeleteRequest {
    private static final String ENDPOINT = "/disk/resources";

    public static Response getMetadata(Map<String, String> parameters) {
        return HttpRequests.getRequest(SharedConstants.HEADERS, SharedConstants.BASE_URI, ENDPOINT, parameters);
    }

    public static Response deleteFile(Map<String, String> parameters) {
        return HttpRequests.deleteRequest(SharedConstants.HEADERS, SharedConstants.BASE_URI, ENDPOINT, parameters);
    }
}
