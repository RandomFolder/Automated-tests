package requests;

import java.io.File;
import java.util.Map;
import constants.SharedConstants;
import io.restassured.response.Response;
import task4.utils.HttpRequests;

public class UploadRequest {
    private static final String UPLOAD = "/disk/resources/upload";

    public static Response uploadFile(File file, Map<String, String> parameters) {
        Response linkRequestResponse = HttpRequests.getRequest(SharedConstants.HEADERS, SharedConstants.BASE_URI, UPLOAD, parameters);
        Response uploadRequestResponse = HttpRequests.putRequest(SharedConstants.HEADERS, linkRequestResponse.jsonPath().get(SharedConstants.LINK_PARAMETER), file);
        
        return uploadRequestResponse;
    }
}
