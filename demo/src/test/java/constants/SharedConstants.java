package constants;

import models.AuthorRecord;
import models.DBConnectionData;
import task5.utils.JSONHandler;

public class SharedConstants {
    public static final DBConnectionData CONNECTION_DATA = JSONHandler.readFile("src/test/resources/db_connection_data.json", DBConnectionData.class);
    public static final AuthorRecord AUTHOR = JSONHandler.readFile("src/test/resources/author_data.json", AuthorRecord.class);
    public static final String PROJECT_NAME = "Databases";
    public static final String BROWSER = "edge";
    public static final String TEST_DB_INSERT_REQUEST = "insert into test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String AUTHOR_DB_INSERT_REQUEST = "insert into author (name, login, email) values (?, ?, ?)";
    public static final String PROJECT_DB_INSERT_REQUEST = "insert into project (name) values (?)";
    public static final String SESSION_DB_INSERT_REQUEST = "insert into session (session_key, created_time, build_number) values (?, ?, ?)";
    public static final String REGEXP_ZERO_OR_MANY_SIGN = "%";
    public static final int ID_COLUMN_INDEX = 1;
    public static final int MAX_EXECUTION_TIME = 10000;
    public static final int MAX_BUILD_NUMBER = 9;
}
