package requests;

import java.util.HashMap;
import java.util.Map;
import constants.SharedConstants;
import constants.TestsCheckConstants;
import exam.utils.HttpRequests;
import io.restassured.response.Response;

public class AllTestsRequest {
    private static final String ENDPOINT = "test/get/json";

    public static Response getAllTests(String projectId) {
        Map<String, String> allTestsRequestParams = new HashMap<>() {{
            put(TestsCheckConstants.PROJECT_ID_PARAM, String.valueOf(projectId));
        }};

        return HttpRequests.postRequest(SharedConstants.API_BASE_URI, ENDPOINT, allTestsRequestParams);
    }
}
