package constants;

import java.util.HashMap;
import java.util.Map;

public class RenameFileStepsConstants {
    public static final String TEMP_FILE_NAME = "f.jpg";
    public static final Map<String, String> RENAME_PARAMETERS = new HashMap<>() {{
        put("from", SharedConstants.TEXT_FILE_NAME);
        put("path", SharedConstants.IMAGE_FILE_NAME);
    }};
    public static final Map<String, String> UPDATE_PARAMETERS = new HashMap<>() {{
        put("from", SharedConstants.IMAGE_FILE_NAME);
        put("path", TEMP_FILE_NAME);
    }};
    public static final Map<String, String> UPDATE_PARAMETERS_REVERSE = new HashMap<>() {{
        put("from", TEMP_FILE_NAME);
        put("path", SharedConstants.IMAGE_FILE_NAME);
    }};    
}
