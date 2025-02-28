package databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import constants.SharedConstants;
import session.DbConnection;

public abstract class Database {
    protected final String SELECT_ID_REQUEST = "select id from %s where %s = ?";
    protected String dbName;

    public Database(String dbName) {
        this.dbName = dbName;
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long selectFirstIdByField(String field, Object value) {
        PreparedStatement statement = null;
        
        try {
            statement = DbConnection.getInstance().getConnection().prepareStatement(String.format(SELECT_ID_REQUEST, this.dbName, field), Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, value);
            
            ResultSet result = statement.executeQuery();
            Long id = null;
            if (result.next()) {
                id = result.getLong(SharedConstants.ID_COLUMN_INDEX);
            }

            return id;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            this.closeStatement(statement);
        }
    }

    public Long execute(String request, Object... params) {
        Long newId = null;

        ResultSet generatedKeys = null;
        PreparedStatement statement = null;

        try {
            statement = DbConnection.getInstance().getConnection().prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            int curIndex = 1;
            for (Object obj : params) {
                statement.setObject(curIndex, obj);
                curIndex++;
            }
            statement.execute();

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newId = generatedKeys.getLong(SharedConstants.ID_COLUMN_INDEX);
            }

            return newId;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            this.closeResultSet(generatedKeys);
            this.closeStatement(statement);
        }
    }

    public Long selectOrAddIfNotPresent(String searchField, Object searchValue, String insertRequest, Object... insertParams) {
        Long resultID = this.selectFirstIdByField(searchField, searchValue);
        if (resultID == null) {
            resultID = this.execute(insertRequest, insertParams);
        }

        return resultID;
    }
}
