package constants;

import exam.utils.JSONHandler;
import models.SessionParameters;

public class AddProjectAndTestConstants {
    public static final String PROJECT_NAME = JSONHandler.readFile(SharedConstants.PATH_TO_SESSION_PARAMS, SessionParameters.class).projectName();
    public static final String SID = "SID";
    public static final String PROJECT_NAME_PARAM = "projectName";
    public static final String TEST_NAME = "testName";
    public static final String METHOD_NAME = "methodName";
    public static final String ENV = "env";
    public static final String TEST_ID = "testId";
    public static final String CONTENT = "content";
    public static final String CONTENT_TYPE = "contentType";
}
