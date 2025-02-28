package databases;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import constants.DatabaseFieldNames;
import constants.SharedConstants;
import models.TestRecord;
import session.DbConnection;

public class TestDB extends Database {
    private final String SELECT_ALL_REQUEST = "select * from test where %s = ?";
    private final String CONDITIONAL_REQUEST = "select convert(id, char) as ID from test where ID like ? limit 10";

    public TestDB() {
        super("test");
    }

    public List<Long> selectIdValuesThatIncludeDigitMultipleTimes(int digit) {
        ResultSet result = null;
        PreparedStatement statement = null;
        String digitAsString = String.valueOf(digit);
        
        try {
            List<Long> idValues = new ArrayList<>();

            statement = DbConnection.getInstance().getConnection().prepareStatement(CONDITIONAL_REQUEST);
            statement.setString(1, SharedConstants.REGEXP_ZERO_OR_MANY_SIGN + digitAsString + digitAsString + SharedConstants.REGEXP_ZERO_OR_MANY_SIGN);
            result = statement.executeQuery();
            
            while (result.next()) {
                idValues.add(result.getLong(DatabaseFieldNames.ID));
            }

            return idValues;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            this.closeResultSet(result);
            this.closeStatement(statement);
        }
    }

    public TestRecord selectById(Long id) {
        ResultSet result = null;
        PreparedStatement statement = null;
        
        try {
            TestRecord record = null;

            statement = DbConnection.getInstance().getConnection().prepareStatement(String.format(SELECT_ALL_REQUEST, DatabaseFieldNames.ID));
            statement.setObject(1, id);
            
            result = statement.executeQuery();
            if (result.next()) {
                String name = result.getString(DatabaseFieldNames.NAME);
                Integer statusID = result.getObject(DatabaseFieldNames.STATUS_ID) != null ? result.getInt(DatabaseFieldNames.STATUS_ID) : null;
                String methodName = result.getString(DatabaseFieldNames.METHOD_NAME);
                Long projectId = result.getLong(DatabaseFieldNames.PROJECT_ID);
                Long sessionId = result.getLong(DatabaseFieldNames.SESSION_ID);
                Timestamp startTime = result.getTimestamp(DatabaseFieldNames.START_TIME);
                Timestamp endTime = result.getTimestamp(DatabaseFieldNames.END_TIME);
                String env = result.getString(DatabaseFieldNames.ENV);
                String browser = result.getString(DatabaseFieldNames.BROWSER);
                Long authorId = result.getObject(DatabaseFieldNames.AUTHOR_ID) != null ? result.getLong(DatabaseFieldNames.AUTHOR_ID) : null;

                record = new TestRecord(name, statusID, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);
            }

            return record;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            this.closeResultSet(result);
            this.closeStatement(statement);
        }
    }
}
