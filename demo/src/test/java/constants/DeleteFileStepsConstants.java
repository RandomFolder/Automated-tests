package constants;

import java.util.HashMap;
import java.util.Map;

public class DeleteFileStepsConstants {
    public static final Map<String, String> IMAGE_FILE_INFO_PARAMETERS = new HashMap<>() {{
        put("path", SharedConstants.IMAGE_FILE_NAME);
        put("fields", "name, created, path");
    }};
    public static final Map<String, String> RECYCLE_BIN_INFO_PARAMETERS = new HashMap<>() {{
        put("path", SharedConstants.PATH_SPLIT);
        put("fields", "_embedded.items.name, _embedded.items.created, _embedded.items.path");
    }};
    public static final Map<String, String> REMOVE_FILE_PARAMETERS = new HashMap<>() {{
        put("path", SharedConstants.IMAGE_FILE_NAME);
        put("permanently", "true");
    }};
    public static final String RESTORE_PATH_PARAMETER = "path";
    public static final String RECYCLE_BIN_ITEMS_PATH = "_embedded/items";
}
