package requests;

import java.util.HashMap;
import java.util.Map;

import constants.DeleteFileStepsConstants;
import constants.SharedConstants;
import io.restassured.response.Response;
import task4.utils.HttpRequests;

public class RestoreRequest {
    private static final String RESTORE = "/disk/trash/resources/restore";

    public static Response recoverFile(String pathToFile) {
        Map<String, String> parameters = new HashMap<>() {{
            put(DeleteFileStepsConstants.RESTORE_PATH_PARAMETER, pathToFile);
        }};

        Response response = HttpRequests.putRequest(SharedConstants.HEADERS, SharedConstants.BASE_URI, RESTORE, parameters);

        return response;
    }
}
