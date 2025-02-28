package constants;

import java.util.HashMap;
import java.util.Map;

import task4.utils.FileHandler;

public class SharedConstants {
    public static final String WEB_RESOURCE_PATH = "https://disk.yandex.ru/";
    public static final String BASE_URI = "https://cloud-api.yandex.net/v1";
    public static final String OAUTH_TOKEN = FileHandler.readTextFile("src/test/resources/token.txt");
    public static final String REQUEST_HEADER = "Authorization";
    public static final String HEADER_VALUE = "OAuth " + OAUTH_TOKEN;
    public static final String LINK_PARAMETER = "href";
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String TEXT_FILE_NAME = "file.txt";
    public static final String IMAGE_FILE_NAME = "file.jpg";
    public static final String PATH_SPLIT = "/";
    public static final int TIMEOUT = 10;
    public static final int POLLING_INTERVAL = 300;
    public static final int TEXT_LENGTH = 50;
    public static final Map<String, String> HEADERS = new HashMap<>() {{
        put(REQUEST_HEADER, HEADER_VALUE);
    }};
}
