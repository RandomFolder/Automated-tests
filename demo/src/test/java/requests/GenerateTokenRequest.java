package requests;

import java.util.Map;
import constants.SharedConstants;
import exam.utils.HttpRequests;
import io.restassured.response.Response;

public class GenerateTokenRequest {
    private static final String ENDPOINT = "/token/get";

    public static Response generateToken(Map<String, String> parameters) {
        return HttpRequests.postRequest(SharedConstants.API_BASE_URI, ENDPOINT, parameters);
    }
}
