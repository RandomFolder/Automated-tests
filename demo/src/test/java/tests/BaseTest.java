package tests;

import org.testng.annotations.AfterTest;
import session.DbConnection;
import session.SessionManager;

public class BaseTest {
    @AfterTest
    public void clearSession() {
        SessionManager.clearSession();
        DbConnection.closeConnection();
    }
}
