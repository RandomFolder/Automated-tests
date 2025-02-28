package requests;

import java.util.HashMap;
import java.util.Map;
import constants.AddProjectAndTestConstants;
import constants.SharedConstants;
import exam.utils.HttpRequests;
import io.restassured.response.Response;

public class AddImageRequest {
    private static final String ENDPOINT = "/test/put/attachment";

    public static Response addImage(String testId, String requestBody) {
        Map<String, String> addImageParams = new HashMap<>() {{
            put(AddProjectAndTestConstants.TEST_ID, testId);
        }};

        return HttpRequests.postRequest(SharedConstants.API_BASE_URI, ENDPOINT, requestBody, SharedConstants.JSON_CONTENT_TYPE, addImageParams);
    }
}
