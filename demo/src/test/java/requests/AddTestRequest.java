package requests;

import java.util.HashMap;
import java.util.Map;

import constants.AddProjectAndTestConstants;
import constants.SharedConstants;
import exam.utils.HttpRequests;
import io.restassured.response.Response;
import models.NewTestInfo;
import session.SessionManager;

public class AddTestRequest {
    private static final String ENDPOINT = "/test/put";

    public static Response addTest(NewTestInfo info) {
        Map<String, String> addTestParams = new HashMap<>() {{
            put(AddProjectAndTestConstants.SID, SessionManager.getSessionManager().getSessionId());
            put(AddProjectAndTestConstants.PROJECT_NAME_PARAM, info.projectName());
            put(AddProjectAndTestConstants.TEST_NAME, info.testName());
            put(AddProjectAndTestConstants.METHOD_NAME, info.testMethodName());
            put(AddProjectAndTestConstants.ENV, info.env());
        }};

        return HttpRequests.postRequest(SharedConstants.API_BASE_URI, ENDPOINT, addTestParams);
    }
}
