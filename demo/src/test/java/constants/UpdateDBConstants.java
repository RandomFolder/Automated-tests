package constants;

public class UpdateDBConstants {
    public static final int MAX_DIGIT = 9;
    public static final int TEST_SUCCESS = 1;
    public static final int TEST_FAILURE = 2;
    public static final int NANOS_ROUND_THRESHOLD = 500000000;
    public static final int ADDITIONAL_SECONDS = 1;
    public static final int TIMESTAMP_NANOS_VALUE = 0;
    public static final String TEST_DB_UPDATE_REQUEST = "update test set status_id = ?, session_id = ?, start_time = ?, end_time = ?, env = ?, browser = ? where id = ?";
    public static final String TEST_DB_DELETE_REQUEST = "delete from test where id = ?";
}
