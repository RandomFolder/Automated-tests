package constants;

import java.util.HashMap;
import java.util.Map;

import exam.utils.JSONHandler;
import models.SessionParameters;

public class RegistrationConstants {
    public static final String COOKIE_NAME = "token";
    public static final int VARIANT = JSONHandler.readFile(SharedConstants.PATH_TO_SESSION_PARAMS, SessionParameters.class).variant();

    public static final Map<String, String> CREATE_TOKEN_PARAMS = new HashMap<>() {{
        put("variant", String.valueOf(VARIANT));
    }};
}
