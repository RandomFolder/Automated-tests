package requests;

import java.util.HashMap;
import java.util.Map;

import constants.AddProjectAndTestConstants;
import constants.SharedConstants;
import exam.utils.HttpRequests;
import io.restassured.response.Response;

public class AddLogRequest {
    private static final String ENDPOINT = "/test/put/log";

    public static Response addLogToTest(String testId, String log) {
        Map<String, String> addLogParams = new HashMap<>() {{
            put(AddProjectAndTestConstants.TEST_ID, testId);
            put(AddProjectAndTestConstants.CONTENT, log);
        }};

        return HttpRequests.postRequest(SharedConstants.API_BASE_URI, ENDPOINT, addLogParams);
    }
}
