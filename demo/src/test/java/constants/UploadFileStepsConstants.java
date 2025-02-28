package constants;

import java.util.HashMap;
import java.util.Map;

public class UploadFileStepsConstants {
    public static final String TEXT_FILE_PATH = "src/test/resources/file.txt";
    public static final String IMAGE_PATH = "src/test/resources/file.jpg";
    public static final Map<String, String> UPLOAD_TEXT_PARAMETERS = new HashMap<>() {{
        put("path", SharedConstants.TEXT_FILE_NAME);
    }};
    public static final Map<String, String> UPLOAD_IMAGE_PARAMETERS = new HashMap<>() {{
        put("path", SharedConstants.IMAGE_FILE_NAME);
        put("overwrite", "true");
    }};
}
