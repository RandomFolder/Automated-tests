package constants;

import exam.utils.JSONHandler;
import models.SessionParameters;
import models.UserData;

public class SharedConstants {
    public static final String PATH_TO_SESSION_PARAMS = "src/test/resources/session_params.json";
    public static final String PATH_TO_USER_DATA = "src/test/resources/user_data.json";
    public static final int PORT;
    public static final String WEB_RESOURCE_PATH;
    public static final String API_BASE_URI;

    static {
        UserData userData = JSONHandler.readFile(PATH_TO_USER_DATA, UserData.class);
        SessionParameters params = JSONHandler.readFile(PATH_TO_SESSION_PARAMS, SessionParameters.class);
        PORT = params.port();
        WEB_RESOURCE_PATH = String.format(params.webUrl(), userData.login(), userData.password(), params.port());
        API_BASE_URI = String.format(params.apiBaseUri(), params.port());
    }
    public static final String EMPTY_STRING = "";
    public static final String DIGITS_REGEX = "[^0-9]";
    public static final String REFERENCE_PARAMETER = "href";
    public static final String IMAGE_CONTENT_TYPE = "image/png";
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String CLASS_NAME_SEPARATOR = "@";
    public static final String METHOD_NAME_PATH_SEPARATOR = ".";
    public static final String PATH_TO_LOG = "target/log/log.log";
    public static final String EQUALS_SIGN = "=";
}
