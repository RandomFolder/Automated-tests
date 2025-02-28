package session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import constants.SharedConstants;
import models.DBConnectionData;

public class DbConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static DbConnection dbConnection = null;
    private Connection connection = null;

    private DbConnection() {

    }

    private static void createConnection() {
        DBConnectionData data = SharedConstants.CONNECTION_DATA;
        dbConnection = new DbConnection();

        try {
            Class.forName(DRIVER);
            dbConnection.connection = DriverManager.getConnection(data.url(), data.user(), data.password());
        }
        catch (Exception e) {
            e.printStackTrace();
            dbConnection.connection = null;
        }
    }

    public static DbConnection getInstance() {
        if (dbConnection == null) {
            createConnection();
            return dbConnection;
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return dbConnection.connection;
    }

    public static void closeConnection() {
        if (dbConnection != null) {
            try {
                dbConnection.connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            dbConnection = null;
        }
    }
}
